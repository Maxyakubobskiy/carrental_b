package carrental.carrental_b.controllers;

import carrental.carrental_b.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TokenValidationController {

    private final JwtUtils jwtUtils;
    public TokenValidationController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/valid-token")
    public ResponseEntity<?> validateToken(@RequestBody Map<String, String> requestBody) {

        String token = requestBody.get("token");

        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        boolean isValid = jwtUtils.isValidUserToken(token);

        Map<String, Boolean> response = new HashMap<>();
        response.put("isValid", isValid);

        return ResponseEntity.ok(response);
    }
}

