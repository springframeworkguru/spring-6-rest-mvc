package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {


    List<Customer> listCustomers();
    Customer getById(UUID Id);

    Customer saveCustomer(Customer customer);

    void deleteById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);

    void updateCustomerById(Customer customer,UUID customerId);
}