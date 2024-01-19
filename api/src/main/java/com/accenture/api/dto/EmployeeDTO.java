package com.accenture.api.dto;

import com.accenture.api.form.RoleName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {

    private Long id;

    private String employeeNumber;

    private String firstName;

    private String lastName;

    private AddressDTO address;

    private BranchDTO branch;

    private RoleName role;

}
