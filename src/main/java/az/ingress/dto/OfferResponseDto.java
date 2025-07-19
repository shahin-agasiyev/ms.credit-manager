package az.ingress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferResponseDto {

    private Long id;
    private Double amount;
    private Integer term;
    private Double interest;
    private Boolean accepted;
    private Long creditId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
