package az.ingress.service;

import az.ingress.dto.CustomerDto;
import az.ingress.dto.CustomerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponseDto create(CustomerDto customerDto);

    Page<CustomerResponseDto> searchCustomers(String pin, String fullName, String phoneNumber, Pageable pageable);
}
