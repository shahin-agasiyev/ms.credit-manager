package az.ingress.service;

import az.ingress.dto.OfferDto;
import az.ingress.dto.OfferResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public interface OfferService {
    OfferResponseDto create(@Valid OfferDto offerDto);

    List<OfferResponseDto> getOffersByCreditId(Long customerId, Long creditId);
}
