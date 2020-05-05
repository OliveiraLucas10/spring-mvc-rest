package com.oliveiralucaspro.springmvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oliveiralucaspro.springmvcrest.api.v1.model.CustomerDTO;
import com.oliveiralucaspro.springmvcrest.api.v1.model.CustomerListDTO;
import com.oliveiralucaspro.springmvcrest.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(CustomerController.BASE_URL)
@RequiredArgsConstructor
public class CustomerController {

    static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
	return new ResponseEntity<>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
	return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
	return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping({ "/{id}" })
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
	return new ResponseEntity<>(customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }

    @PatchMapping({ "/{id}" })
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
	return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping({ "/{id}" })
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
	customerService.deleteCustomerById(id);
	return new ResponseEntity<>(HttpStatus.OK);
    }
}
