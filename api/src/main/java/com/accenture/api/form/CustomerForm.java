package com.accenture.api.form;

import com.accenture.api.dto.AddressDTO;
import com.accenture.api.dto.ContactDataDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerForm {

    @NotBlank
    private String customerNumber;

    private AddressDTO address;

    private ContactDataDTO contactData;

    private CustomerTypeName customerType;

    @Min(1)
    private Long employeeId;

    @NotBlank
    private String cif;

}
