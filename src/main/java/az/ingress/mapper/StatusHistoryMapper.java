package az.ingress.mapper;

import az.ingress.dto.StatusHistoryResponseDto;
import az.ingress.model.StatusHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusHistoryMapper {

    StatusHistoryResponseDto toStatusHistoryResponseDto(StatusHistory saveStatus);
}
