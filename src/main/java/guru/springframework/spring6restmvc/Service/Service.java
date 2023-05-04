package guru.springframework.spring6restmvc.Service;

import guru.springframework.spring6restmvc.Customer.CustomerDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@org.springframework.stereotype.Service

public class Service implements ServiceIntefaceforGet{

    Map<UUID, CustomerDto> id = new HashMap<>();

    public Collection<CustomerDto> returnCustomer() {
       return this.id.values();
    }

    public CustomerDto returnCustomerOfId(UUID id) {
        return this.id.get(id);
    }

    @Override
    public CustomerDto HandlePost(CustomerDto customerDto) {
  customerDto.builder().Customerid(UUID.randomUUID()).
          Customername(customerDto.getCustomername()).
          CustomerDate(customerDto.getCustomerDate())
          .CustomerVersion(customerDto.getCustomerVersion())
          .lastModfiedDate(customerDto.getLastModfiedDate()).
          build();

        id.put(customerDto.getCustomerid(), customerDto);
        return customerDto;
    }

    @Override
    public void updatebyId(UUID id, CustomerDto customerDto) {
        CustomerDto customerDto1 = this.id.get(id);
        customerDto1.setCustomername(customerDto.getCustomername());
        customerDto1.setCustomerDate(customerDto.getCustomerDate());
        customerDto1.setCustomerVersion(customerDto.getCustomerVersion());
        customerDto1.setLastModfiedDate(customerDto.getLastModfiedDate());
        this.id.put(id, customerDto1);
    }

    @Override
    public void DeletebyId(UUID id) {
        this.id.remove(id);
    }
}
