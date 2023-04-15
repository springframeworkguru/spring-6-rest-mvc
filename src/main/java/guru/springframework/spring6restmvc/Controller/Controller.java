package guru.springframework.spring6restmvc.Controller;

import guru.springframework.spring6restmvc.Customer.Customer;
import guru.springframework.spring6restmvc.Service.ServiceIntefaceforGet;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor // this is a lombok annotation that will create a constructor for us
//for the dependencies that we have in this class
public class Controller {
    private ServiceIntefaceforGet serviceIntefaceforGet;

    @RequestMapping(value = "/api/v1/customers",method = RequestMethod.GET)
    public Collection<Customer> returnCustomer() {
        return this.serviceIntefaceforGet.returnCustomer();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)// making it specfic to get method
    public Customer returnCustomerOfId(@PathVariable UUID id) {
        return this.serviceIntefaceforGet.returnCustomerOfId(id);
    }

}
