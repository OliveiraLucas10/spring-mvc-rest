package com.oliveiralucaspro.springmvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oliveiralucaspro.springmvcrest.api.v1.model.VendorDTO;
import com.oliveiralucaspro.springmvcrest.api.v1.model.VendorListDTO;
import com.oliveiralucaspro.springmvcrest.services.VendorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(VendorController.BASE_URL)
@RequiredArgsConstructor
public class VendorController {

    static final String BASE_URL = "/api/v1/vendor";

    private final VendorService vendorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
	return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
	return vendorService.getVendorById(id);
    }
}
