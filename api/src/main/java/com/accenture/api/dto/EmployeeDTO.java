package com.accenture.api.dto;

import lombok.Data;

@Data
public class EmployeeDTO {


    private String employeeNumber;

    private String firstName;

    private String lastName;

    private AddressDTO address;

    private BranchDTO branch;

    private RoleDTO role;

}
