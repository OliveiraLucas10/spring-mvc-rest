package com.oliveiralucaspro.springmvcrest.controllers.v1;

import static org.hamcrest.Matchers.hasSize;
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

import com.oliveiralucaspro.springmvcrest.api.v1.model.VendorDTO;
import com.oliveiralucaspro.springmvcrest.controllers.RestResponseEntityExceptionHandler;
import com.oliveiralucaspro.springmvcrest.services.VendorService;

class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	mockMvc = MockMvcBuilders.standaloneSetup(controller)
		.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void testGetAllVendors() throws Exception {
	List<VendorDTO> vendorDTOs = Arrays.asList(new VendorDTO(), new VendorDTO());

	when(vendorService.getAllVendors()).thenReturn(vendorDTOs);

	mockMvc.perform(get(VendorController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$.vendors", hasSize(2)));

    }

}
