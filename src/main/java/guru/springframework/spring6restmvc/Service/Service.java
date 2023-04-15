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

}
