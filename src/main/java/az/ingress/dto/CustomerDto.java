package az.ingress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    @NotBlank
    private String pin;

    @NotBlank
    private String fullName;

    @NotBlank
    private String phoneNumber;
}
