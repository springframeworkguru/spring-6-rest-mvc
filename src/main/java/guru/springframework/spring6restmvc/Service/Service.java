package guru.springframework.spring6restmvc.Service;

import guru.springframework.spring6restmvc.Customer.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@org.springframework.stereotype.Service

public class Service implements ServiceIntefaceforGet{

    Map<UUID, Customer> id = new HashMap<>();

    public Collection<Customer> returnCustomer() {
       return this.id.values();
    }

    public Customer returnCustomerOfId(UUID id) {
        return this.id.get(id);
    }

    @Override
    public  Customer HandlePost(Customer customer) {
  customer.builder().Customerid(UUID.randomUUID()).
          Customername(customer.getCustomername()).
          CustomerDate(customer.getCustomerDate())
          .CustomerVersion(customer.getCustomerVersion())
          .lastModfiedDate(customer.getLastModfiedDate()).
          build();

        id.put(customer.getCustomerid(),customer);
        return customer;
    }

    @Override
    public void updatebyId(UUID id, Customer customer) {
        Customer customer1 = this.id.get(id);
        customer1.setCustomername(customer.getCustomername());
        customer1.setCustomerDate(customer.getCustomerDate());
        customer1.setCustomerVersion(customer.getCustomerVersion());
        customer1.setLastModfiedDate(customer.getLastModfiedDate());
        this.id.put(id,customer1);
    }

    @Override
    public void DeletebyId(UUID id) {
        this.id.remove(id);
    }
}
