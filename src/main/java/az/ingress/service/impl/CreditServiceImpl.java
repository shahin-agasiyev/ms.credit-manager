package az.ingress.service.impl;

import az.ingress.constant.Status;
import az.ingress.dto.CreditDto;
import az.ingress.dto.CreditResponseDto;
import az.ingress.mapper.CreditMapper;
import az.ingress.mapper.OfferMapper;
import az.ingress.mapper.StatusHistoryMapper;
import az.ingress.model.Credit;
import az.ingress.model.Customer;
import az.ingress.model.Offer;
import az.ingress.model.StatusHistory;
import az.ingress.repository.CreditRepository;
//import az.ingress.repository.OfferRepository;
import az.ingress.repository.CustomerRepository;
import az.ingress.repository.OfferRepository;
import az.ingress.repository.StatusHistoryRepository;
import az.ingress.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;
    private final CustomerRepository customerRepository;
    private final OfferRepository offerRepository;
    private final StatusHistoryRepository statusHistoryRepository;
    private final StatusHistoryMapper statusHistoryMapper;
    private final OfferMapper offerMapper;

    @Override
    public CreditResponseDto initializeCredit(Long customerId, CreditDto creditDto) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Credit credit = Credit.builder()
                .requestedAmount(creditDto.getRequestedAmount())
                .createdAt(LocalDateTime.now())
                .checkDate(LocalDateTime.now().plusDays(2))
                .status(Status.DRAFT)
                .customerId(customerId)
                .build();

        Credit save = creditRepository.save(credit);

        StatusHistory statusHistory = StatusHistory.builder()
                .status(Status.DRAFT)
                .createdAt(LocalDateTime.now())
                .creditId(credit.getId())
                .build();

        StatusHistory saveStatus = statusHistoryRepository.save(statusHistory);
        statusHistoryMapper.toStatusHistoryResponseDto(saveStatus);

        return creditMapper.toCreditResponseDto(save);
    }

    @Override
    public List<CreditResponseDto> getCreditByStatus(Status status) {
        return creditRepository.findAllByStatus(status)
                .stream()
                .map(creditMapper::toCreditResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditResponseDto acceptCredit(Long offerId, Long customerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        Credit credit = creditRepository.findById(offer.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found"));

        credit.setAmount(offer.getAmount());
        credit.setStatus(Status.ACCEPTED);
        credit.setInterest(offer.getInterest());
        credit.setUpdatedAt(LocalDateTime.now());
        credit.setTerm(offer.getTerm());
        credit.setCustomerId(customerId);
        credit.setMonthlyPayment(calculateMonthlyPayment(offer.getInterest(), offer.getAmount(), offer.getTerm()));

        Credit save = creditRepository.save(credit);

        offer.setAccepted(true);
        Offer savedOffer = offerRepository.save(offer);
        offerMapper.toOfferResponseDto(savedOffer);

        StatusHistory statusHistory = StatusHistory.builder()
                .creditId(credit.getId())
                .createdAt(LocalDateTime.now())
                .status(Status.ACCEPTED)
                .build();

        statusHistoryRepository.save(statusHistory);

        return creditMapper.toCreditResponseDto(save);
    }

    @Override
    public CreditResponseDto rejectCredit(Long creditId, Long customerId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        credit.setStatus(Status.REJECTED);
        credit.setUpdatedAt(LocalDateTime.now());

        Credit save = creditRepository.save(credit);

        StatusHistory statusHistory = StatusHistory.builder()
                .creditId(credit.getId())
                .createdAt(LocalDateTime.now())
                .status(Status.REJECTED)
                .build();

        StatusHistory saveStatus = statusHistoryRepository.save(statusHistory);
        statusHistoryMapper.toStatusHistoryResponseDto(saveStatus);

        return creditMapper.toCreditResponseDto(save);
    }

    private Double calculateMonthlyPayment(Double interest, Double amount, int term) {
        return (amount + amount * interest / 100) / term;
    }
}
