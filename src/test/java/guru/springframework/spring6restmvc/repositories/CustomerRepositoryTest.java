package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void TestCustomerSave() {
        Customer customer = customerRepository.save(
                Customer.builder()
                        .customerName("New Customer")
                        .build()
        );
        assertNotNull(customer);
        assertNotNull(customer.getId());

    }

}