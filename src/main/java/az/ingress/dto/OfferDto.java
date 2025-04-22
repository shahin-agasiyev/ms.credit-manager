package az.ingress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto {

    @NotNull
    private Double amount;

    @NotNull
    private Integer term;

    @NotNull
    private Double interest;

    @NotNull
    private Long creditId;
}
