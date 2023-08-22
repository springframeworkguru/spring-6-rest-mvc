package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDto> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        CustomerDto customer1 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("John Doe")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerDto customer2 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Maximarket Inc.")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
    }

    @Override
    public List<CustomerDto> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDto> getCustomerById(UUID id) {
        log.debug("Get Customer by Id - in service. Id: {}", id.toString());

        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customer) {

        CustomerDto savedCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .name(customer.getName())
                .version(1)
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public Boolean deleteById(UUID customerId) {
        customerMap.remove(customerId);
        return true;
    }

    @Override
    public Optional<CustomerDto> updateCustomerById(UUID customerId, CustomerDto customer) {

        CustomerDto existing = customerMap.get(customerId);

        if (customer.getName() != null) {
            existing.setName(customer.getName());
        }

        // Always refresh automatically calculated values
        existing.setVersion(existing.getVersion() + 1);
        existing.setUpdatedDate(LocalDateTime.now());

        customerMap.put(existing.getId(), existing);

        return Optional.of(existing);
    }
}
