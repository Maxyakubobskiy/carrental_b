package carrental.carrental_b.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RentDto {
    private Long carId;
    private LocalDate rentFrom;
    private LocalDate rentTo;
    private Double rentAmount;
    private PaymentData paymentData;

    @Data
    public static class PaymentData {
        private String method;
        private String cardNumber;
        private String expiryDate;
        private String cvv;
    }
}
