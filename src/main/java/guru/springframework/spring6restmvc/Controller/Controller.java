package guru.springframework.spring6restmvc.Controller;

import guru.springframework.spring6restmvc.Customer.CustomerDto;
import guru.springframework.spring6restmvc.Service.ServiceIntefaceforGet;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor // this is a lombok annotation that will create a constructor for us
//for the dependencies that we have in this class
public class Controller {
    private ServiceIntefaceforGet serviceIntefaceforGet;
@DeleteMapping("{id}")
    public ResponseEntity DeletebyId(@PathVariable("id") UUID id) {
        this.serviceIntefaceforGet.DeletebyId(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
@PutMapping("{id}")
    public ResponseEntity updatebyId(@PathVariable("id") UUID id, @RequestBody CustomerDto customerDto) {
        this.serviceIntefaceforGet.updatebyId(id, customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        //HttpStatus.NO_CONTENT is an HTTP status code that represents a response
    // from a server to indicate that a request has been successfully processed,
    // but there is no content to return. In other words, it means that the server has
    // completed the request successfully, but there is no data to send back in the
    // response body.

    //HttpStatus.NO_CONTENT is often used in scenarios where the request
    // is used to perform an action on the server, such as updating a resource or
    // deleting a resource, and the response does not need to include any additional data.
    // It is commonly used in RESTful APIs to indicate that a request has been successfully
    // processed without returning a response body.
    }
@PostMapping("/api/v1/customers")
    public ResponseEntity HandlePost(@RequestBody CustomerDto customerDto) // telling spring
        // Post it to the body of the request that what @RequestBody is doing
     {
      CustomerDto customerDto1 = this.serviceIntefaceforGet.HandlePost(customerDto);
         HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location","/api/v1/customers/"+ customerDto1.getCustomerid().toString());
            //Above is adding the location and this is best practise
      return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/v1/customers",method = RequestMethod.GET)
    public Collection<CustomerDto> returnCustomer() {
        return this.serviceIntefaceforGet.returnCustomer();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)// making it specfic to get method
    public CustomerDto returnCustomerOfId(@PathVariable UUID id) {
        return this.serviceIntefaceforGet.returnCustomerOfId(id);
    }

}
