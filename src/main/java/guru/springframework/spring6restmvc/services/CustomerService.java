package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.*;

import java.util.*;

public interface CustomerService {
    Customer getCustomerById(UUID id);
    List<Customer> listAllCustomers();
    Customer saveNewCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);
}
