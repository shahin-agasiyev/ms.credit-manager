package az.ingress.service;

import az.ingress.constant.Status;
import az.ingress.dto.CreditDto;
import az.ingress.dto.CreditResponseDto;

import java.util.List;

public interface CreditService {
    CreditResponseDto initializeCredit(Long customerId, CreditDto creditDto);

    List<CreditResponseDto> getCreditByStatus(Status status);

    CreditResponseDto acceptCredit(Long offerId, Long customerId);

    CreditResponseDto rejectCredit(Long creditId, Long customerId);

}
