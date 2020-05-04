package com.oliveiralucaspro.springmvcrest.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.oliveiralucaspro.springmvcrest.api.v1.model.CustomerDTO;
import com.oliveiralucaspro.springmvcrest.services.CustomerService;

class CustomerControllerTest {

    private static final String LAST_NAME = "Favela";

    private static final String FIRST_NAME = "Pedro";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testGetAllCustomers() throws Exception {
	// given
	CustomerDTO customerDTO1 = new CustomerDTO();
	customerDTO1.setFirstName(FIRST_NAME);
	customerDTO1.setLastName(LAST_NAME);

	CustomerDTO customerDTO2 = new CustomerDTO();
	customerDTO2.setFirstName("Serio");
	customerDTO2.setLastName("Mariola");

	List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO1, customerDTO2);
	when(customerService.getAllCustomers()).thenReturn(customerDTOList);

	mockMvc.perform(get("/api/v1/customers/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void testGetCustomerById() throws Exception {
	// given
	CustomerDTO customerDTO1 = new CustomerDTO();
	customerDTO1.setFirstName(FIRST_NAME);
	customerDTO1.setLastName(LAST_NAME);

	when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO1);

	mockMvc.perform(get("/api/v1/customers/2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

}
