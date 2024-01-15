package com.accenture.api.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;

    private String customerNumber;

    private AddressDTO address;

    private ContactDataDTO contactData;

    private CustomerTypeDTO customerType;

    private EmployeeDTO employee;

    private String cif;
}
