package com.oliveiralucaspro.springmvcrest.services;

import java.util.List;

import com.oliveiralucaspro.springmvcrest.api.v1.model.VendorDTO;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

}
