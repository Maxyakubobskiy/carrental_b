package carrental.carrental_b.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long paymentId;
    private String type;
    private Double amount;
    private LocalDateTime dateTime;
    private Boolean status;

    public PaymentDto(Long paymentId, String type, Double amount, LocalDateTime dateTime, Boolean status) {
        this.paymentId = paymentId;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
        this.status = status;
    }
}
