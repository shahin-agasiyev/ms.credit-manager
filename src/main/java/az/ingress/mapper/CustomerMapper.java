package az.ingress.mapper;

import az.ingress.dto.CustomerDto;
import az.ingress.dto.CustomerResponseDto;
import az.ingress.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

//    Customer toEntity(CustomerDto customerDto);

    CustomerResponseDto toCustomerResponseDto(Customer customer);
}
