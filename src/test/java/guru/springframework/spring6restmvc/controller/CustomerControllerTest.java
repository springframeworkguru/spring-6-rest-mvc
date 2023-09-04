package guru.springframework.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.config.SpringSecurityConfigOAuth2;
import guru.springframework.spring6restmvc.model.CustomerDto;
import guru.springframework.spring6restmvc.services.CustomerService;
import guru.springframework.spring6restmvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@Import(SpringSecurityConfigOAuth2.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    // OAuth 2.0
    public static final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtRequestPostProcessor =
            jwt().jwt(jwt -> {
                jwt.claims(claims -> {
                            claims.put("scope", "message.read");
                            claims.put("scope", "message.write");
                        })
                        .subject("messaging-client")
                        .notBefore(Instant.now().minusSeconds(5));
            });
    // =========================================
    // HTTP Basic Authentication
    // =========================================
    // @Value("${spring.security.user.name}")
    // private String USERNAME;
    // -----------------------------------------
    // @Value("${spring.security.user.password}")
    // private String PASSWORD;
    // =========================================

    @BeforeEach
    void setUp() {
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    void testDeleteCustomer() throws Exception {
        CustomerDto customer = customerServiceImpl.listCustomers().get(0);

        given(customerService.deleteById(any())).willReturn(true);

        mockMvc.perform(
                    delete(CustomerController.CUSTOMER_PATH + "/" + customer.getId())
                        .with(jwtRequestPostProcessor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(customerService).deleteById(uuidArgumentCaptor.capture());

        assertThat(customer.getId())
                .isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testUpdateCustomer() throws Exception {
        CustomerDto customer = customerServiceImpl.listCustomers().get(0);

        given(customerService.updateCustomerById(any(), any())).willReturn(Optional.of(customer));

        mockMvc.perform(
                    put(CustomerController.CUSTOMER_PATH + "/" + customer.getId())
                        .with(jwtRequestPostProcessor)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());

        verify(customerService).updateCustomerById(any(UUID.class), any(CustomerDto.class));

    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerDto customer = customerServiceImpl.listCustomers().get(0);
        customer.setVersion(null);
        customer.setId(null);

        given(customerService.saveNewCustomer(any(CustomerDto.class)))
                .willReturn(customerServiceImpl.listCustomers().get(1));

        mockMvc.perform(
                    post(CustomerController.CUSTOMER_PATH)
                        .with(jwtRequestPostProcessor)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testListCustomers() throws Exception {
        given(customerService.listCustomers())
                .willReturn(customerServiceImpl.listCustomers());

        mockMvc.perform(
                    get(CustomerController.CUSTOMER_PATH)
                        .with(jwtRequestPostProcessor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void getCustomerByIdNotFound() throws Exception {

        given(customerService.getCustomerById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(
                    get(CustomerController.CUSTOMER_PATH + "/" + UUID.randomUUID())
                        .with(jwtRequestPostProcessor))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCustomerNotAuthenticated() throws Exception {

        given(customerService.getCustomerById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(
                    get(CustomerController.CUSTOMER_PATH + "/" + UUID.randomUUID()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDto testCustomer = customerServiceImpl.listCustomers().get(0);

        given(customerService.getCustomerById(testCustomer.getId()))
                .willReturn(Optional.of(testCustomer));

        mockMvc.perform(
                    get(CustomerController.CUSTOMER_PATH + "/" + testCustomer.getId())
                        .with(jwtRequestPostProcessor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is(testCustomer.getName())));
    }
}
