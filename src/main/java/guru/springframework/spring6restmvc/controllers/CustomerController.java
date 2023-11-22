package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.model.*;
import guru.springframework.spring6restmvc.services.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;
    @GetMapping(value = CUSTOMER_PATH)
    public List<CustomerDTO> listAllCustomers(){
        return customerService.listAllCustomers();
    }


    @GetMapping(value = CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId){
        log.debug("Get beer by id - controller");
        return  customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody CustomerDTO customer){
        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer/" + savedCustomer.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomerById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer){
        customerService.updateCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomerById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer){
        customerService.patchCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomerById(@PathVariable UUID customerId){
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
