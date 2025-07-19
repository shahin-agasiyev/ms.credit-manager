package az.ingress.controller;

import az.ingress.dto.CustomerDto;
import az.ingress.dto.CustomerResponseDto;
import az.ingress.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponseDto create(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.create(customerDto);
    }

    @GetMapping("/find-by-spec")
    public Page<CustomerResponseDto> searchCustomers(
            @RequestParam(required = false) String pin,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String phoneNumber,
            @PageableDefault(value = 15) Pageable pageable) {
        return customerService.searchCustomers(pin, fullName, phoneNumber, pageable);
    }

}
