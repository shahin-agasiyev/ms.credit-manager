package az.ingress.controller;

import az.ingress.dto.OfferDto;
import az.ingress.dto.OfferResponseDto;
import az.ingress.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public OfferResponseDto create(@Valid
                                   @RequestBody OfferDto offerDto) {
        return offerService.create(offerDto);
    }

    @GetMapping("/{customerId}/by-credit")
    public List<OfferResponseDto> getOffersByCreditId(@PathVariable Long customerId,
                                                      @RequestParam Long creditId) {
        return offerService.getOffersByCreditId(customerId, creditId);
    }
}
