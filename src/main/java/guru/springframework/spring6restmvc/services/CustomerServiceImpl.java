package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.controllers.*;
import guru.springframework.spring6restmvc.model.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.math.*;
import java.time.*;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private final Map<UUID, Customer> customerMap;
    public CustomerServiceImpl() {
        customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Test Testov")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Vasya Pupkin")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
    }

    @Override
    public  Optional<Customer> getCustomerById(UUID id) {
        return Optional.of(customerMap.get(id));
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .build();
        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {
        Customer existing = this.getCustomerById(customerId).orElseThrow(NotFoundException::new);
        existing.setCustomerName(customer.getCustomerName());
        existing.setLastModifiedDate(LocalDateTime.now());
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = this.getCustomerById(customerId).orElseThrow(NotFoundException::new);
        if (StringUtils.hasText(customer.getCustomerName())){
            existing.setCustomerName(customer.getCustomerName());
        }
    }
}
