package az.ingress.service.impl;

import az.ingress.dto.CustomerDto;
import az.ingress.dto.CustomerResponseDto;
import az.ingress.mapper.CustomerMapper;
import az.ingress.mapper.OfferMapper;
import az.ingress.model.Customer;
import az.ingress.repository.CustomerRepository;
import az.ingress.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto create(CustomerDto dto) {

        Customer customer = Customer.builder()
                .pin(dto.getPin())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .build();

        Customer saveCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerResponseDto(saveCustomer);
    }

    @Override
    public Page<CustomerResponseDto> searchCustomers(String pin,
                                                     String fullName,
                                                     String phoneNumber,
                                                     Pageable pageable) {
        Specification<Customer> specification;
        specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (pin != null) {
                predicates.add(criteriaBuilder.equal(root.get("pin"), pin));
            }

            if (fullName != null) {
                predicates.add(criteriaBuilder.equal(root.get("fullName"), fullName));
            }

            if (phoneNumber != null) {
                predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));
            }

//            if (predicates.isEmpty()) {
//                return (Predicate) Page.empty();
//            }


            Objects.requireNonNull(query).where(
                    criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])))
            );
            return query.getRestriction();
        };
        return customerRepository.findAll(specification, pageable).map(customerMapper::toCustomerResponseDto);
    }
}
