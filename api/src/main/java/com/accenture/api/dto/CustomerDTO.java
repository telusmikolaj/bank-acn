package com.accenture.api.dto;

import com.accenture.api.form.CustomerTypeName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private Long id;

    private String customerNumber;

    private AddressDTO address;

    private ContactDataDTO contactData;

    private CustomerTypeName customerType;

    private EmployeeDTO employee;

    private String cif;
}
