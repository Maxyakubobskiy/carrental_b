package carrental.carrental_b.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String firstName;
    private String lastName;
}
