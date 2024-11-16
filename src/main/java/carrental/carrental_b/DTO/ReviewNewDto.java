package carrental.carrental_b.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewNewDto {
    private Long reviewId;
    private Byte rating;
    private String comment;
    private String username;
    private Long userId;
}
