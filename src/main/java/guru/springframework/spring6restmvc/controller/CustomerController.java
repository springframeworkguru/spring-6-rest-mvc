package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH= "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID= CUSTOMER_PATH+ "/{customerId}";
    private final CustomerService customerService;
    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("customerId") UUID customerId){
        customerService.deleteById(customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer){
         customerService.updateCustomerById(customer, customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity saveCustomer(@RequestBody Customer customer){
         Customer savedCustomer= customerService.saveCustomer(customer);
         HttpHeaders header=new HttpHeaders();
         header.add("Location",CUSTOMER_PATH+ savedCustomer.getId().toString());
        return new ResponseEntity(header, HttpStatus.CREATED);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> listCustomers(){
        return  customerService.listCustomers();
    }
    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getById(@PathVariable("customerId") UUID customerId){

        return customerService.getById(customerId);
    }


}