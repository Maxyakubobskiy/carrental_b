package carrental.carrental_b.controllers;

import carrental.carrental_b.DTO.LoginDto;
import carrental.carrental_b.DTO.RegisterDto;
import carrental.carrental_b.roles.Role;
import carrental.carrental_b.services.AuthService;
import carrental.carrental_b.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            String token = authService.authenticate(loginDto.getUsername(), loginDto.getPassword(), Role.ROLE_USER.name());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        Map<String, String> errors = new HashMap<>();

        if (userService.isUsernameAlreadyTaken(registerDto.getUsername())) {
            errors.put("username", "Користувач з таким логіном існує");
        }
        if (userService.isEmailAlreadyTaken(registerDto.getEmail())) {
            errors.put("email", "Користувач з такою поштою існує");
        }
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        if (userService.register(registerDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }
}
