package az.ingress.mapper;

import az.ingress.dto.OfferResponseDto;
import az.ingress.model.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferResponseDto toOfferResponseDto(Offer offer);
}
