package com.accenture.entity.repository;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.exception.CustomDataAccessException;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.form.SearchRequestDTO;
import com.accenture.entity.mapper.CustomerMapper;
import com.accenture.entity.model.customer.Customer;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.entity.util.SampleDataFactory;
import com.accenture.entity.util.SearchCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class CustomerJPADataAccessServiceTest {

    public static final String CUSTOMER_NUMBER_COLUMN = "customerNumber";
    public static final String EXISTING_CUSTOMER_NUMBER = "CUST001";
    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");
    private final FiltersSpecification<Customer> filtersSpecification = new FiltersSpecification<>();

    @Autowired
    CustomerRepository customerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    private CustomerJPADataAccessService underTest;
    @InjectMocks
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @BeforeEach
    void setUp() {
        this.underTest = new CustomerJPADataAccessService(customerRepository, customerMapper, employeeRepository, filtersSpecification);
    }

    @Test
    void shouldCreateCustomerWhenEmployeeExists() {
        //given
        CustomerForm customerForm = SampleDataFactory.getSampleCustomerForm();
        //when
        when(employeeRepository.findById(customerForm.getEmployeeId())).thenReturn(Optional.of(new Employee()));
        CustomerDTO customerDTO = this.underTest.create(customerForm);
        //then
        assertThat(customerDTO.getCustomerNumber()).isEqualTo(customerForm.getCustomerNumber());

    }

    @Test
    void shouldFailedWhenEmployeeNotExists() {
        //given
        CustomerForm customerForm = SampleDataFactory.getSampleCustomerForm();
        //when
        when(employeeRepository.findById(customerForm.getEmployeeId())).thenReturn(Optional.empty());
        //then
        assertThatThrownBy(() -> this.underTest.create(customerForm)).isInstanceOf(EntityNotFoundException.class);

    }

    @Test
    void shouldFailedWhenRequiredFieldIsNull() {
        //given
        CustomerForm customerForm = SampleDataFactory.getSampleCustomerForm();
        customerForm.setCustomerType(null);
        //when
        when(employeeRepository.findById(customerForm.getEmployeeId())).thenReturn(Optional.of(new Employee()));
        //then
        assertThatThrownBy(() -> this.underTest.create(customerForm)).isInstanceOf(CustomDataAccessException.class);

    }

    @Test
    void shouldFailedWhenFieldIsNotUnique() {
        //given
        CustomerForm customerForm = SampleDataFactory.getSampleCustomerForm();
        customerForm.setCustomerNumber(EXISTING_CUSTOMER_NUMBER);
        //when
        when(employeeRepository.findById(customerForm.getEmployeeId())).thenReturn(Optional.of(new Employee()));
        //then
        assertThatThrownBy(() -> this.underTest.create(customerForm)).isInstanceOf(CustomDataAccessException.class);

    }

    @Test
    void shouldReturnCustomerPortfolioWhenEmployeeExists() {
        //given

        //when
        when(this.employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));
        List<CustomerDTO> portfolio = this.underTest.getPortfolio(1L);
        //then
        assertThat(portfolio.size()).isEqualTo(2);

    }

    @Test
    void shouldSearchCustomerByFieldWhenExists() {
        //given
        SearchCondition searchCondition = createSearchCondition(CUSTOMER_NUMBER_COLUMN, EXISTING_CUSTOMER_NUMBER, SearchRequestDTO.Operation.EQUAL);
        //when
        List<CustomerDTO> customerDTOS = this.underTest.searchCustomers(searchCondition.getSearchRequest(List.of(searchCondition), RequestSearchForm.GlobalOperator.AND));
        //then
        assertThat(customerDTOS.stream()
                .anyMatch(customerDTO ->
                        customerDTO.getCustomerNumber().equals(EXISTING_CUSTOMER_NUMBER)))
                .isTrue();

    }

    private SearchCondition createSearchCondition(String column, String value, SearchRequestDTO.Operation operation) {
        return SearchCondition.builder()
                .column(column)
                .value(value)
                .operation(operation)
                .build();
    }


}