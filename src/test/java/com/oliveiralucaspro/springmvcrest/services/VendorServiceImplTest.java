package com.oliveiralucaspro.springmvcrest.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oliveiralucaspro.springmvcrest.api.v1.mapper.VendorMapper;
import com.oliveiralucaspro.springmvcrest.api.v1.model.VendorDTO;
import com.oliveiralucaspro.springmvcrest.domain.Vendor;
import com.oliveiralucaspro.springmvcrest.repositories.VendorRepository;

class VendorServiceImplTest {

    private static final Long ID = 1L;

    private static final String NAME = "Name Test";

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

    @Test
    void testGetVendorById() {
	// given
	Vendor vendor = new Vendor();
	vendor.setId(ID);
	vendor.setName(NAME);

	when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));
	// when
	VendorDTO vendorDTO = service.getVendorById(ID);

	// then
	assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test()
    void testGetVendorByIdNotFound() {
	// given

	when(vendorRepository.findById(anyLong())).thenReturn(Optional.empty());
	// when

	// then
	Assertions.assertThrows(ResourceNotFoundException.class, () -> service.getVendorById(ID));
    }

}
