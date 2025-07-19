package az.ingress.service.impl;

import az.ingress.dto.OfferDto;
import az.ingress.dto.OfferResponseDto;
import az.ingress.mapper.OfferMapper;
import az.ingress.model.Credit;
import az.ingress.model.Customer;
import az.ingress.model.Offer;
import az.ingress.repository.CreditRepository;
import az.ingress.repository.CustomerRepository;
import az.ingress.repository.OfferRepository;
import az.ingress.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;
    private final CreditRepository creditRepository;
    private final CustomerRepository customerRepository;

    @Override
    public OfferResponseDto create(OfferDto offerDto) {

//        Credit credit = creditRepository.findById(offerDto
//                .getCreditId()).orElseThrow(()->new RuntimeException("credit not found"));

        Offer offer = Offer.builder()
                .amount(offerDto.getAmount())
                .term(offerDto.getTerm())
                .interest(offerDto.getInterest())
                .creditId(offerDto.getCreditId())
                .accepted(false)
                .createdAt(LocalDateTime.now())
                .build();

        Offer save = offerRepository.save(offer);
        return offerMapper.toOfferResponseDto(save);
    }

    @Override
    public List<OfferResponseDto> getOffersByCreditId(Long customerId, Long creditId) {

        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found"));

        if (credit.getCustomerId() != customerId){
            throw new RuntimeException("You don't have permission");
        }

        return offerRepository.findByCreditId(creditId).stream()
                .map(offerMapper::toOfferResponseDto)
                .collect(Collectors.toList());
    }
}
