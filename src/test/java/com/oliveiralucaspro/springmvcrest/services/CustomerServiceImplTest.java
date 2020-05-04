package com.oliveiralucaspro.springmvcrest.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oliveiralucaspro.springmvcrest.api.v1.mapper.CustomerMapper;
import com.oliveiralucaspro.springmvcrest.api.v1.model.CustomerDTO;
import com.oliveiralucaspro.springmvcrest.domain.Customer;
import com.oliveiralucaspro.springmvcrest.repositories.CustomerRepository;

class CustomerServiceImplTest {

    private static final String LAST_NAME = "Favela";

    private static final String FIRST_NAME = "Pedro";

    private static final Long ID = 1L;

    @Mock
    CustomerRepository customerRepository;

    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	customerServiceImpl = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void testGetAllCustomers() {
	// given
	List<Customer> customers = Arrays.asList(new Customer(), new Customer());

	when(customerRepository.findAll()).thenReturn(customers);
	// when
	List<CustomerDTO> allCustomers = customerServiceImpl.getAllCustomers();

	// then
	assertEquals(2, allCustomers.size());
    }

    @Test
    void testGetCustomerByFirstName() {
	// given
	Customer customer = new Customer();
	customer.setId(ID);
	customer.setFirstName(FIRST_NAME);
	customer.setLastName(LAST_NAME);

	when(customerRepository.findByFirstName(anyString())).thenReturn(customer);
	// when
	CustomerDTO customerByFirstName = customerServiceImpl.getCustomerByFirstName(FIRST_NAME);

	// then
	assertEquals(ID, customerByFirstName.getId());
	assertEquals(FIRST_NAME, customerByFirstName.getFirstName());
	assertEquals(LAST_NAME, customerByFirstName.getLastName());
    }

}
