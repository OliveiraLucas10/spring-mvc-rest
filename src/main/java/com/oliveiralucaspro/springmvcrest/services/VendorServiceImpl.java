package com.oliveiralucaspro.springmvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.oliveiralucaspro.springmvcrest.api.v1.mapper.VendorMapper;
import com.oliveiralucaspro.springmvcrest.api.v1.model.VendorDTO;
import com.oliveiralucaspro.springmvcrest.repositories.VendorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public List<VendorDTO> getAllVendors() {
	return vendorRepository.findAll().stream().map(vendorMapper::vendorToVendorDTO).collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
	return vendorRepository.findById(id).map(vendorMapper::vendorToVendorDTO)
		.orElseThrow(ResourceNotFoundException::new);
    }

}
