package guru.springframework.spring6restmvc.Service;

import guru.springframework.spring6restmvc.Customer.Customer;

import java.util.Collection;
import java.util.UUID;

public interface ServiceIntefaceforGet {

    public Collection<Customer> returnCustomer();

    public Customer returnCustomerOfId(UUID id);

    Customer  HandlePost(Customer customer);

    void updatebyId(UUID id, Customer customer);

    void DeletebyId(UUID id);
}
