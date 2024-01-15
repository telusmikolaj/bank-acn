package com.accenture.api.form;

import com.accenture.api.dto.AddressDTO;
import com.accenture.api.dto.ContactDataDTO;
import com.accenture.api.dto.CustomerTypeDTO;
import com.accenture.api.dto.EmployeeDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerForm {

    private String customerNumber;

    private AddressDTO address;

    private ContactDataDTO contactData;

    private CustomerTypeDTO customerType;

    private EmployeeDTO employee;
}
