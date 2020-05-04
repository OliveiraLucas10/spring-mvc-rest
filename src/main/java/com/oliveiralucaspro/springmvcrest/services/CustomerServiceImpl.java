package com.oliveiralucaspro.springmvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.oliveiralucaspro.springmvcrest.api.v1.mapper.CustomerMapper;
import com.oliveiralucaspro.springmvcrest.api.v1.model.CustomerDTO;
import com.oliveiralucaspro.springmvcrest.domain.Customer;
import com.oliveiralucaspro.springmvcrest.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
	return customerRepository.findAll().stream().map(customer -> {
	    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
	    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
	    return customerDTO;
	}).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
	return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO)
		.orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
	Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
	Customer customerSaved = customerRepository.save(customer);
	CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(customerSaved);
	returnCustomerDTO.setCustomerUrl("/api/v1/customers/" + customerSaved.getId());
	return returnCustomerDTO;
    }

}
