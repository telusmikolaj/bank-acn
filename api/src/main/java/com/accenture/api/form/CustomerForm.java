package com.accenture.api.form;

import com.accenture.api.dto.AddressDTO;
import com.accenture.api.dto.ContactDataDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerForm {

    private String customerNumber;

    private AddressDTO address;

    private ContactDataDTO contactData;

    private CustomerTypeName customerType;

    private Long employeeId;
}
