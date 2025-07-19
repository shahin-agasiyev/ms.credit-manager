package az.ingress.model;


import az.ingress.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Integer term;
    private Double interest;
    private Double monthlyPayment;

    @Column(nullable = false)
    private Double requestedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime checkDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
    private Long customerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
