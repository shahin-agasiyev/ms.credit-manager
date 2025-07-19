package az.ingress.controller;

import az.ingress.constant.Status;
import az.ingress.dto.CreditDto;
import az.ingress.dto.CreditResponseDto;
import az.ingress.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/credits")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping("/{customerId}")
    public CreditResponseDto initializeCredit(
            @PathVariable Long customerId,
            @Valid @RequestBody CreditDto creditDto) {
        return creditService.initializeCredit(customerId, creditDto);
    }

    @GetMapping("/by-status")
    public List<CreditResponseDto> getCreditByStatus(@RequestParam Status status) {
        return creditService.getCreditByStatus(status);
    }

    @PutMapping("/{customerId}/accept")
    public CreditResponseDto acceptCredit(@RequestParam Long offerId, @PathVariable Long customerId) {
        return creditService.acceptCredit(offerId, customerId);
    }

    @PutMapping("/reject")
    public CreditResponseDto rejectCredit(@RequestParam Long creditId, Long customerId) {
        return creditService.rejectCredit(creditId, customerId);
    }
}