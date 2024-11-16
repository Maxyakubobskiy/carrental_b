package carrental.carrental_b.services;

import carrental.carrental_b.DTO.RegisterDto;
import carrental.carrental_b.DTO.UserDto;
import carrental.carrental_b.models.Car;
import carrental.carrental_b.models.Order;
import carrental.carrental_b.models.User;
import carrental.carrental_b.repository.CarRepository;
import carrental.carrental_b.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean deletUser(String name) {
        User user = userRepository.findByUsername(name).orElse(null);
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

}
