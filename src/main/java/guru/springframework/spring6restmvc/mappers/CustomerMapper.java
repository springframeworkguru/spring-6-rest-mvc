package guru.springframework.spring6restmvc.mappers;

import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDto dto);
    CustomerDto customerToCustomerDto(Customer customer);
}
