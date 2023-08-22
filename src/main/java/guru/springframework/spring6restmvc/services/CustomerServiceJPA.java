package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.mappers.CustomerMapper;
import guru.springframework.spring6restmvc.model.CustomerDto;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> listCustomers() {
        return null;
    }

    @Override
    public Optional<CustomerDto> getCustomerById(UUID id) {
        return Optional.empty();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customer) {
        return null;
    }

    @Override
    public void deleteById(UUID customerId) {

    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDto customer) {

    }
}
