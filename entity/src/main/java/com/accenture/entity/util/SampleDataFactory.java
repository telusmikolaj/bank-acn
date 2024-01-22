package com.accenture.entity.util;

import com.accenture.api.dto.*;
import com.accenture.api.form.*;
import com.accenture.entity.model.customer.Customer;
import com.accenture.entity.model.employee.Employee;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
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

    public static MeetingForm getSampleActvityForm() {
        return MeetingForm.builder()
                .address(getSampleAddressForm())
                .type(ActivityType.MEETING)
                .date(LocalDateTime.now())
                .status(ActivityStatus.CANCELED)
                .customerNumber(getFakeCustomerNumber())
                .employeeNumber("EMP" + faker.business().creditCardNumber())
                .build();
    }

    public static AddressForm getSampleAddressForm() {
        return AddressForm.builder()
                .country(faker.address().country())
                .street(faker.address().streetAddress())
                .city(faker.address().city())
                .postalCode(faker.address().zipCode())
                .province(faker.address().state())
                .build();
    }

    public static ActivityDTO getSampleActvityDTO() {
        return ActivityDTO.builder()
                .status(ActivityStatus.CANCELED)
                .date(LocalDateTime.now())
                .type(ActivityType.MEETING)
                .customerNumber(getFakeCustomerNumber())
                .employeeNumber("EMP" + faker.business().creditCardNumber())
                .build();
    }

    public static Customer getSampleCustomer() {
        return Customer.builder()
                .id(1L)
                .customerNumber(getFakeCustomerNumber())
                .customerType(CustomerTypeName.PREMIUM)
                .build();
    }

    public static Employee getSampleEmployee() {
        return Employee.builder()
                .id(1L)
                .build();
    }

    private static String getFakeCustomerNumber() {
        return "CUST" + faker.business().creditCardNumber();
    }


}
