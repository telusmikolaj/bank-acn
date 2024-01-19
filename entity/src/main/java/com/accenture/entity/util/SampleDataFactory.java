package com.accenture.entity.util;

import com.accenture.api.dto.AddressDTO;
import com.accenture.api.dto.BranchDTO;
import com.accenture.api.dto.ContactDataDTO;
import com.accenture.api.dto.EmployeeDTO;
import com.accenture.api.form.*;
import com.github.javafaker.Faker;

import java.util.List;

public class SampleDataFactory {

    private static final Faker faker = new Faker();

    private SampleDataFactory() {
    }

    public static CustomerForm getSampleCustomerForm() {
        return CustomerForm.builder()
                .customerNumber("CUST" + faker.business().creditCardNumber())
                .cif("CUST" + faker.business().creditCardNumber())
                .address(getSampleAddressDto())
                .contactData(getSampleContactDataDTO())
                .customerType(CustomerTypeName.PREMIUM)
                .employeeId(1L)
                .build();
    }

    public static EmployeeDTO getSampleEmployeeDto() {
        return EmployeeDTO.builder()
                .employeeNumber("EMP" + faker.business().creditCardNumber())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .address(getSampleAddressDto())
                .branch(getSampleBranchDto())
                .role(RoleName.SALES)
                .build();
    }

    public static AddressDTO getSampleAddressDto() {
        return AddressDTO.builder()
                .country(faker.address().country())
                .postalCode(faker.address().zipCode())
                .street(faker.address().state())
                .city(faker.address().city())
                .build();
    }

    public static ContactDataDTO getSampleContactDataDTO() {
        return ContactDataDTO.builder()
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .build();
    }

    public static RequestSearchForm getSampleRequestSearchFormForCustomer() {
        return RequestSearchForm.builder()
                .searchRequestDTO(List.of(
                        SearchRequestDTO.builder()
                                .column("customerNumber")
                                .value("CUST001")
                                .operation(SearchRequestDTO.Operation.EQUAL)
                                .build()
                ))
                .build();
    }

    public static BranchDTO getSampleBranchDto() {
        return BranchDTO.builder()
                .address(getSampleAddressDto())
                .build();
    }

}
