package guru.springframework.spring6restmvc.Service;

import guru.springframework.spring6restmvc.Customer.CustomerDto;

import java.util.Collection;
import java.util.UUID;

public interface ServiceIntefaceforGet {

    public Collection<CustomerDto> returnCustomer();

    public CustomerDto returnCustomerOfId(UUID id);

    CustomerDto HandlePost(CustomerDto customerDto);

    void updatebyId(UUID id, CustomerDto customerDto);

    void DeletebyId(UUID id);
}
