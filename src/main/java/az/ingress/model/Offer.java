package az.ingress.model;

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
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer term;

    @Column(nullable = false)
    private Double interest;

    private Boolean accepted;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "credit_id")
    private Long creditId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
