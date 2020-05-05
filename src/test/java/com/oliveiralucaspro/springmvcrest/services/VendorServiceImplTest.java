package com.oliveiralucaspro.springmvcrest.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oliveiralucaspro.springmvcrest.api.v1.mapper.VendorMapper;
import com.oliveiralucaspro.springmvcrest.api.v1.model.VendorDTO;
import com.oliveiralucaspro.springmvcrest.domain.Vendor;
import com.oliveiralucaspro.springmvcrest.repositories.VendorRepository;

class VendorServiceImplTest {

    @Mock
    VendorRepository vendorRepository;

    VendorServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	service = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void testGetAllVendors() {
	// given
	List<Vendor> vendorList = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

	when(vendorRepository.findAll()).thenReturn(vendorList);
	// when
	List<VendorDTO> allVendors = service.getAllVendors();

	// then
	assertEquals(vendorList.size(), allVendors.size());
    }

}
