package carrental.carrental_b.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Long orderId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private PaymentDto payment;

    public OrderDto(Long orderId, LocalDate startDate, LocalDate endDate, String status, Long userId, String username, String firstName, String lastName) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.userId = userId;
        this.userName = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
