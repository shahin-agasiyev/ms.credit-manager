package az.ingress.mapper;

import az.ingress.dto.CreditResponseDto;
import az.ingress.model.Credit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    CreditResponseDto toCreditResponseDto(Credit credit);
}
