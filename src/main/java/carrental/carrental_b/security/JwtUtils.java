package carrental.carrental_b.security;

import carrental.carrental_b.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    private final UserRepository userRepository;
    @Value("${jwt.secret}")
    public String secret;

    @Value("${jwt.expiration-time}")
    public Long lifeTime;

    private SecretKey key;

    public JwtUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void init() {
        if (secret != null) {
            this.key = Keys.hmacShaKeyFor(secret.getBytes());
        } else {
            throw new IllegalArgumentException("JWT secret cannot be null");
        }
    }

    public String generateToken(Authentication auth) {
        if (key == null) {
            init();
        }

        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + lifeTime);

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key)
                .compact();
    }

    public String getNameFromToken(String token) {
        if (key == null) {
            init();
        }
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean isValidToken(String token) {
        if (key == null) {
            init();
        }
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parse(token);
            return true; // Токен валідний
        } catch (ExpiredJwtException e) {
            System.out.println("Токен прострочений: " + e.getMessage());
        } catch (JwtException e) {
            System.out.println("Невалідний токен: " + e.getMessage());
        }
        return false;
    }

    public boolean isValidUserToken(String token) {
        if (isValidToken(token)) {
            String username = getNameFromToken(token);
            if (username != null) {
                return userRepository.existsByUsername(username);
            }
        }
        return false;
    }

}
