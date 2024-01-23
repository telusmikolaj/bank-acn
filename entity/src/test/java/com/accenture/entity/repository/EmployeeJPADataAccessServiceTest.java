package com.accenture.entity.repository;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.entity.mapper.EmployeeMapper;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.specification.FiltersSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmployeeJPADataAccessServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");
    private final FiltersSpecification<Employee> filtersSpecification = new FiltersSpecification<>();

    @Autowired
    private EmployeeRepository employeeRepository;

    private FiltersSpecification<Employee> filter;

    @Spy
    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    private EmployeeJPADataAccessService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new EmployeeJPADataAccessService(employeeRepository, filter, employeeMapper);
    }

    @Test
    void shouldGetSubordinatesWhenEmployeeExists() {
        String existingEmployeeNumber = "EMP001";
        List<EmployeeDTO> subordinates = this.underTest.getSubordinates(existingEmployeeNumber);
        assertThat(subordinates).isNotNull();
        assertThat(subordinates.size()).isEqualTo(1);
    }

    @Test
    void shouldNotGetSubordinatesWhenEmployeeNotExists() {
        String notExistingEmployee = "0";
        assertThatThrownBy(() -> this.underTest.getSubordinates(notExistingEmployee)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldGetSupervisorWhenExists() {
        String existingEmployeeNumber = "EMP001";
        EmployeeDTO supervisor = this.underTest.getSupervisor(existingEmployeeNumber);
        assertThat(supervisor).isNotNull();
    }

    @Test
    void shouldNotGetSupervisorWhenEmployeeNotExists() {
        String notExistingEmployee = "0";
        assertThatThrownBy(() -> this.underTest.getSubordinates(notExistingEmployee)).isInstanceOf(EntityNotFoundException.class);
    }



}