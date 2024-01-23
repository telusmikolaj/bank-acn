package util;

import com.accenture.api.dto.*;
import com.accenture.api.form.*;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static ProductDTO getSampleProductDTO() {
        return ProductDTO.builder()
                .productNumber(faker.business().creditCardNumber())
                .id(1L)
                .balance(BigDecimal.valueOf(100))
                .openingDate(LocalDate.now())
                .accountNumber(faker.business().creditCardNumber())
                .customer(getSampleCustomerDto())
                .type("LEASING")
                .build();

    }

    public static List<ProductDTO> getSampleProductDtoList() {
        return List.of(getSampleProductDTO(), getSampleProductDTO());
    }

    public static List<EmployeeDTO> getSampleEmployeeDtoList() {
        return List.of(getSampleEmployeeDto(), getSampleEmployeeDto());
    }

    public static ExposureDTO getSampleExposureDto() {
        ExposureDTO exposureDTO = new ExposureDTO();
        exposureDTO.setExposures(new HashMap<>());
        exposureDTO.addProductInfo(getSampleProductDTO());

        return exposureDTO;
    }

    public static ActivitySummaryDTO getSampleActivitySummaryDto() {
        return ActivitySummaryDTO.builder()
                .activites(Map.of(
                        ActivityType.CALL, Map.of(ActivityStatus.CANCELED, 1L),
                        ActivityType.MEETING, Map.of(ActivityStatus.SCHEDULED, 5L),
                        ActivityType.OFFER, Map.of(ActivityStatus.COMPLETED, 2L)
                ))
                .build();
    }

}
