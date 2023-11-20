package guru.springframework.spring6restmvc.controllers;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import guru.springframework.spring6restmvc.model.*;
import guru.springframework.spring6restmvc.services.*;
import org.assertj.core.api.*;
import org.hamcrest.core.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp(){
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    void updateCustomer() throws Exception {
        Customer testCustomer = customerServiceImpl.listAllCustomers().get(0);
        mockMvc.perform(put("/api/v1/customer/" + testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(testCustomer)))
                .andExpect(status().isNoContent())
        ;
        verify(customerService).updateCustomerById(any(UUID.class), any(Customer.class));

    }

    @Test
    void deleteCustomer() throws Exception {
        Customer testCustomer = customerServiceImpl.listAllCustomers().get(0);
        mockMvc.perform(delete("/api/v1/customer/" + testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        ;
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(customerService).deleteCustomerById(argumentCaptor.capture());
        AssertionsForClassTypes.assertThat(testCustomer.getId()).isEqualTo(argumentCaptor.getValue());
    }

    @Test
    void createCustomer() throws Exception {
        Customer testCustomer = customerServiceImpl.listAllCustomers().get(0);
        testCustomer.setId(null);
        given(customerService.saveNewCustomer(any(Customer.class))).willReturn(customerServiceImpl.listAllCustomers().get(1));
        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(testCustomer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
        ;
    }

    @Test
    void listAllCustomers() throws Exception {
        given(customerService.listAllCustomers()).willReturn(customerServiceImpl.listAllCustomers());

        mockMvc.perform(get("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Is.is(2)))
        ;
    }

    @Test
    void getCustomerById() throws Exception {
        Customer testCustomer = customerServiceImpl.listAllCustomers().get(0);
        given(customerService.getCustomerById(any(UUID.class))).willReturn(testCustomer);

        mockMvc.perform(get("/api/v1/customer/" + testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(testCustomer.getId().toString())))
                .andExpect(jsonPath("$.customerName", Is.is(testCustomer.getCustomerName())))
        ;
    }
}