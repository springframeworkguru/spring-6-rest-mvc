package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{
       private Map<UUID, Customer> customerMap;
       public CustomerServiceImpl(){
           this.customerMap= new HashMap<>();

           Customer customer1= Customer.builder()
                   .customerName("henos")
                   .Id(UUID.randomUUID())
                   .createdDate(LocalDate.now())
                   .lastModifiedDate(LocalDate.now())
                   .version(new BigDecimal(1))
                   .build();

           Customer customer2= Customer.builder()
                   .customerName("lidya")
                   .Id(UUID.randomUUID())
                   .createdDate(LocalDate.now())
                   .lastModifiedDate(LocalDate.now())
                   .version(new BigDecimal(1))
                   .build();
           Customer customer3= Customer.builder()
                   .customerName("ghirmay")
                   .Id(UUID.randomUUID())
                   .createdDate(LocalDate.now())
                   .lastModifiedDate(LocalDate.now())
                   .version(new BigDecimal(1))
                   .build();

           customerMap.put(customer1.getId(),customer1);
           customerMap.put(customer2.getId(),customer2);
           customerMap.put(customer3.getId(),customer3);
       }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }
    }

    @Override
    public Customer getById(UUID Id) {
        return customerMap.get(Id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer customerSaved= Customer.builder()
                .customerName(customer.getCustomerName())
                .Id(UUID.randomUUID())
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .version(new BigDecimal(1))
                .build();
        customerMap.put(customerSaved.getId(),customerSaved);
        return customerSaved;
    }



    @Override
    public void updateCustomer(Customer customer,UUID customerId) {
        Customer existingCustomer= customerMap.get(customerId);
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setVersion(customer.getVersion());

    }


    @Override
    public void deleteById(UUID customerId){
           customerMap.remove(customerId);
    }


}