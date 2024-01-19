package util;

import com.accenture.api.dto.*;
import com.accenture.api.form.*;
import com.github.javafaker.Faker;

import java.util.List;

public class SampleDataFactory {

    private static final Faker faker = new Faker();

    private SampleDataFactory() {
    }

    public static CustomerForm getSampleCustomerForm() {
        return CustomerForm.builder()
                .customerNumber(getFakeCustomerNumber())
                .cif(getFakeCustomerNumber())
                .address(getSampleAddressDto())
                .contactData(getSampleContactDataDTO())
                .customerType(CustomerTypeName.PREMIUM)
                .employeeId(1L)
                .build();
    }

    public static CustomerDTO getSampleCustomerDto() {
        return CustomerDTO.builder()
                .customerType(CustomerTypeName.PREMIUM)
                .cif(getFakeCustomerNumber())
                .customerNumber(getFakeCustomerNumber())
                .contactData(getSampleContactDataDTO())
                .employee(getSampleEmployeeDto())
                .address(getSampleAddressDto())
                .build();
    }

    public static EmployeeDTO getSampleEmployeeDto() {
        return EmployeeDTO.builder()
                .id(1L)
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

    public static BranchDTO getSampleBranchDto() {
        return BranchDTO.builder()
                .address(getSampleAddressDto())
                .build();
    }

    public static List<CustomerDTO> getSampleCustomerDtoList() {
        return List.of(
                getSampleCustomerDto(),
                getSampleCustomerDto(),
                getSampleCustomerDto()
        );
    }

    public static RequestSearchForm getSampleRequestSearchForm() {
        return RequestSearchForm.builder()
                .searchRequestDTO(List.of(SearchRequestDTO.builder().build()))
                .globalOperator(RequestSearchForm.GlobalOperator.AND)
                .build();
    }


    private static String getFakeCustomerNumber() {
        return "CUST" + faker.business().creditCardNumber();
    }

}
