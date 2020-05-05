package com.oliveiralucaspro.springmvcrest.api.v1.model;

import java.util.List;

import com.oliveiralucaspro.springmvcrest.domain.Vendor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VendorListDTO {

    List<Vendor> vendors;

}
