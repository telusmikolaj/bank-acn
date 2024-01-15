package com.accenture.entity.repository;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.form.SearchRequestDTO;
import com.accenture.entity.mapper.*;
import com.accenture.entity.model.Customer;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.entity.util.SampleDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class CustomerJPADataAccessServiceTest {

    public static final String CUSTOMER_NUMBER_COLUMN = "customerNumber";
    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");
    private final FiltersSpecification<Customer> filtersSpecification = new FiltersSpecification<>();

    @Autowired
    CustomerRepository customerRepository;
    private CustomerJPADataAccessService underTest;
    @InjectMocks
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Spy
    private AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
    @Spy
    private ContactDataMapper contactDataMapper = Mappers.getMapper(ContactDataMapper.class);

    @Spy
    private CustomerTypeMapper customerTypeMapper = Mappers.getMapper(CustomerTypeMapper.class);
    @Spy
    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @Spy
    private BranchMapper branchMapper = Mappers.getMapper(BranchMapper.class);

    @BeforeEach
    void setUp() {
        this.underTest = new CustomerJPADataAccessService(customerRepository, customerMapper, filtersSpecification);
    }

    @Test
    void create() {
        //given
        CustomerForm customerForm = SampleDataFactory.getSampleCustomerForm();
        //when
        CustomerDTO customerDTO = this.underTest.create(customerForm);
        //then
        assertThat(customerDTO.getCustomerNumber()).isEqualTo(customerForm.getCustomerNumber());

    }

    @Test
    void searchCustomersByCustomerNumberColumn() {
        //given
        CustomerForm sampleCustomerForm = SampleDataFactory.getSampleCustomerForm();
        Customer customer = this.customerMapper.toCustomer(sampleCustomerForm);
        Customer savedCustomer = this.customerRepository.save(customer);

        //when
        List<CustomerDTO> customerDTOS =
                this.underTest.searchCustomers(getRequestSearchFormForCustomer(
                        customer, CUSTOMER_NUMBER_COLUMN, SearchRequestDTO.Operation.EQUAL));

        //then
        assertThat(customerDTOS.size()).isEqualTo(1);
        assertThat(customerDTOS.stream()
                .anyMatch(customerDTO -> customerDTO.getCustomerNumber().equals(savedCustomer.getCustomerNumber())))
                .isTrue();
    }

    private RequestSearchForm getRequestSearchFormForCustomer(Customer customer, String columnName, SearchRequestDTO.Operation operation) {
        SearchRequestDTO searchRequestDTO = buildSearchRequestDTO(columnName, customer.getCustomerNumber(), operation);
        return buildRequestSearchForm(List.of(searchRequestDTO));
    }

    private RequestSearchForm buildRequestSearchForm(List<SearchRequestDTO> searchRequestDTOS) {
        return RequestSearchForm.builder()
                .searchRequestDTO(searchRequestDTOS)
                .globalOperator(RequestSearchForm.GlobalOperator.AND)
                .build();
    }

    private SearchRequestDTO buildSearchRequestDTO(String columnName, String value, SearchRequestDTO.Operation operation) {
        return SearchRequestDTO.builder()
                .column(columnName)
                .value(value)
                .operation(operation)
                .build();
    }

}