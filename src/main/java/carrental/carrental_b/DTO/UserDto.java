package carrental.carrental_b.DTO;

import carrental.carrental_b.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String firstName;
    private String lastName;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.dateOfBirth = user.getDateOfBirth();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
