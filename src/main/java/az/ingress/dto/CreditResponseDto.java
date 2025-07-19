package az.ingress.dto;

import az.ingress.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditResponseDto {

    private Long id;
    private Double amount;
    private Integer term;
    private Double interest;
    private Double monthlyPayment;
    private Double requestedAmount;
    private String status;
    private LocalDateTime checkDate;
    private Long customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
