package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.Customer.Customer;
import guru.springframework.spring6restmvc.Service.Service;
import guru.springframework.spring6restmvc.Service.ServiceIntefaceforGet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static net.bytebuddy.matcher.ElementMatchers.is;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.UUID;

@WebMvcTest(CustomerController.class) // This annotation is used to test
// the controller layer of the application and we are limiting this to the CustomerController
//Rather then all the controllers in the application
public class CustomerController {

@Autowired
    MockMvc mockMvc; // This is used to mock the MVC layer of the application

    //Returning Data using Mockito
    Service service = new Service();

@MockBean// tell mockito to mock this in the spring context and inject it into the controlle
    ServiceIntefaceforGet serviceIntefaceforGet;

@Test
void testUpdateCustomer() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper(); //creating the Jackson api to create Json
    objectMapper.findAndRegisterModules();//Making the module
        ServiceIntefaceforGet serviceIntefaceforGet = new Service();
        Customer customer = serviceIntefaceforGet.returnCustomer().stream().findFirst().get(); //getting the first customer

        mockMvc.perform(put("/api/v1/customer/") //using the WEB
                .accept(MediaType.APPLICATION_JSON) //Accepting the Json format of the data
                .contentType(MediaType.APPLICATION_JSON) // The content type of the data is Json
                .content(objectMapper.writeValueAsString(customer))); // converting customer to json

          verify(serviceIntefaceforGet).updatebyId(customer.getCustomerid(),any(Customer.class)); // Verifying the update method
    // and checking if the customer id is same as the customer id in the customer object

    }

    @Test
    //Delete
    void testDeleteCustomer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(); //creating the Jackson api to create Json
        objectMapper.findAndRegisterModules();//Making the module
        ServiceIntefaceforGet serviceIntefaceforGet = new Service();
        Customer customer = serviceIntefaceforGet.returnCustomer().stream().findFirst().get(); //getting the first customer

        mockMvc.perform(delete("/api/v1/customer/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
//It is used to capture the arguments passed to a method during a test.
//
//When a method is called during a test, Mockito can capture the arguments passed to that method using an ArgumentCaptor.
// The ArgumentCaptor can then be used to inspect the captured argument and perform various assertions on it.

        //So right now we are capturing the id and then comparing it with the customer id and see if it the same
        verify(serviceIntefaceforGet).DeletebyId(argumentCaptor.capture());
        assertThat(customer.getCustomerid()).equals(argumentCaptor.getValue());
        


    }

    

//Creating Json using the Jackson API


    @Test
    void testCreateNewCustomer() throws JsonProcessingException {
      Customer customer = Customer.builder().Customername("John").build();
      customer.setCustomerDate("12/12/2020");
        customer.setCustomerVersion(String.valueOf(1));
        customer.setLastModfiedDate("12/12/2020");
        given(serviceIntefaceforGet.HandlePost(any(Customer.class))).willReturn(customer);

    }

    @Test
    void TestListOfBeers() throws Exception
     {
        given(serviceIntefaceforGet.returnCustomer()).willReturn(service.returnCustomer());
        mockMvc.perform(get("/api/v1/customer/").accept(MediaType.APPLICATION_JSON))
                //Testing and checking the length
                .andExpect(status().isOk()).andExpect((ResultMatcher) jsonPath("$.length()", is(3)));
    }
    @Test
    void getBeerById() throws Exception {

        Customer testCustomer = service.returnCustomer().stream().findFirst().get();
        //telling mockito here that when the method returnCustomerOfId is called with any UUID
        // then return the testCustomer which is the First Customer in the Hashmap
        given(serviceIntefaceforGet.returnCustomerOfId(any(UUID.class))).willReturn(testCustomer);

    //using the web framework to test the controller
        mockMvc.perform(get("/api/v1/customer/" + UUID.randomUUID()).
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk())
        .andExpect((ResultMatcher) jsonPath("$.id", is(testCustomer.getCustomerid().toString())))
        .andExpect((ResultMatcher) jsonPath("$.Customername", is("John"))); // checking
        //if the Custoner name matches the name of the first customer in the hashmap


;
    }
}
