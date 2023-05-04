package guru.springframework.spring6restmvc.Repositories;

import guru.springframework.spring6restmvc.entites.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CustomerRepostioryTest {

    @Autowired
    CustomerRepostiory customerRepostiory;

    @Test
    void testCustomerRepository(){
       Customer customer = customerRepostiory.save(Customer.builder().build());
         assertNotNull(customer);
    }

}