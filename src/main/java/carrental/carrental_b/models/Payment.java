package carrental.carrental_b.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "card_id")
    private Card card;

    private String type;
    private Double amount;
    private LocalDateTime dateTime;
    private Boolean status;

    public Payment(Order order, Card card, String method, Double rentAmount, LocalDateTime now, boolean b) {
        this.order = order;
        this.card = card;
        this.type = method;
        this.amount = rentAmount;
        this.dateTime = now;
        this.status = b;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", status=" + status +
                '}';
    }
}

