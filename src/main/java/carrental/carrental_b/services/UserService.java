package carrental.carrental_b.services;

import carrental.carrental_b.DTO.RegisterDto;
import carrental.carrental_b.DTO.UserDto;
import carrental.carrental_b.models.Car;
import carrental.carrental_b.models.Order;
import carrental.carrental_b.models.User;
import carrental.carrental_b.repository.CarRepository;
import carrental.carrental_b.repository.UserRepository;
import carrental.carrental_b.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CarRepository carRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.carRepository = carRepository;
    }

    public boolean register(RegisterDto registerDto) {
        User user = new User(
                registerDto.getUsername(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                registerDto.getPhoneNumber(),
                registerDto.getDateOfBirth(),
                registerDto.getFirstName(),
                registerDto.getLastName()
        );
        user.setRole(Role.ROLE_USER);
        user.setLocked(false);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isUsernameAlreadyTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailAlreadyTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getUserInfo(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    public ResponseEntity<?> getInfoResponse(String username) {
        User user = getUserInfo(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserDto(user));
    }

    public boolean updateUser(String username, UserDto userDto) {
        User currentUser = userRepository.findByUsername(username).orElse(null);
        if (currentUser != null) {

            if (isUsernameOrEmailTakenByOtherUser(userDto.getUsername(), userDto.getEmail(), currentUser)) {
                return false;
            }
            currentUser.setUsername(userDto.getUsername());
            currentUser.setEmail(userDto.getEmail());
            currentUser.setPhoneNumber(userDto.getPhoneNumber());
            currentUser.setDateOfBirth(userDto.getDateOfBirth());
            currentUser.setFirstName(userDto.getFirstName());
            currentUser.setLastName(userDto.getLastName());
            try {
                userRepository.save(currentUser);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean deleteUser(String name) {
        User user = userRepository.findByUsername(name).orElse(null);
        return deleteUserByObject(user);
    }

    public boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return deleteUserByObject(user);
    }

    private boolean deleteUserByObject(User user) {
        if (user == null) {
            return false;
        }
        try {
            List<Car> cars = user.getOrders().stream()
                    .map(Order::getCar)
                    .peek(car -> car.setAvailable(true))
                    .toList();
            carRepository.saveAll(cars);
            userRepository.delete(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isUsernameOrEmailTakenByOtherUser(String username, String email, User currentUser) {
        User userWithSameUsername = userRepository.findByUsername(username).orElse(null);
        User userWithSameEmail = userRepository.findByEmail(email).orElse(null);

        return (userWithSameUsername != null && !userWithSameUsername.getUserId().equals(currentUser.getUserId())) ||
                (userWithSameEmail != null && !userWithSameEmail.getUserId().equals(currentUser.getUserId()));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> !Role.ROLE_ADMIN.equals(user.getRole()))
                .collect(Collectors.toList());
    }

    public boolean blockUser(Long userId) {
        User user =  userRepository.findById(userId).orElse(null);
        if (user == null || user.isLocked()) {
            return false;
        }
        user.setLocked(true);
        userRepository.save(user);
        return true;
    }

    public boolean unblockUser(Long userId) {
        User user =  userRepository.findById(userId).orElse(null);
        if (user == null || !user.isLocked()) {
            return false;
        }
        user.setLocked(false);
        userRepository.save(user);
        return true;
    }
}
