package com.accenture.entity.repository;

import com.accenture.api.dto.CustomerDTO;
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


    public SearchCondition createSearchCondition(String column, String value, SearchRequestDTO.Operation operation, String joinTable, String joinField) {
        SearchCondition.SearchConditionBuilder condition = SearchCondition.builder()
                .column(column)
                .value(value)
                .operation(operation);

        if (isJoinTableAndFieldProvided(joinTable, joinField)) {
            condition.joinTable(joinTable);
            condition.joinField(joinField);
        }

        return condition.build();
    }

    public RequestSearchForm createComplexRequestSearchForm(List<SearchCondition> conditions, RequestSearchForm.GlobalOperator globalOperator) {
        List<SearchRequestDTO> dtos = conditions.stream()
                .map(condition -> buildSearchRequestDTO(condition.getColumn(), condition.getValue(), condition.getOperation(), condition.getJoinTable(), condition.getJoinField()))
                .toList();

        return buildRequestSearchForm(dtos, globalOperator);
    }

    private RequestSearchForm buildRequestSearchForm(List<SearchRequestDTO> searchRequestDTOS, RequestSearchForm.GlobalOperator globalOperator) {
        return RequestSearchForm.builder()
                .searchRequestDTO(searchRequestDTOS)
                .globalOperator(globalOperator)
                .build();
    }

    private SearchRequestDTO buildSearchRequestDTO(String column, String value, SearchRequestDTO.Operation operation, String joinTable, String joinField) {
        SearchRequestDTO.SearchRequestDTOBuilder builder = SearchRequestDTO.builder()
                .column(column)
                .value(value)
                .operation(operation);

        if (isJoinTableAndFieldProvided(joinTable, joinField)) {
            builder.joinTable(joinTable)
                    .joinField(joinField);
        }

        return builder.build();
    }

    private boolean isJoinTableAndFieldProvided(String joinTable, String joinField) {
        return joinTable != null && !joinTable.isEmpty() && joinField != null && !joinField.isEmpty();
    }

}