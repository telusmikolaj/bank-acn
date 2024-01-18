package com.accenture.entity.repository;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.entity.mapper.EmployeeMapper;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.service.EmployeeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeJPADataAccessService implements EmployeeDao {

    private final EmployeeRepository employeeRepository;

    private final FiltersSpecification<Employee> filter;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> search(RequestSearchForm requestSearchForm) {

        return this.employeeRepository.findAll(this.filter.getSearchSpecification(requestSearchForm))
                .stream()
                .map(this.employeeMapper::toDto)
                .toList();

    }

    @Override
    public List<EmployeeDTO> getSubordinates(String employeeNumber) {
        return this.employeeRepository.findAllBySupervisorEmployeeNumber(employeeNumber).stream()
                .map(this.employeeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDTO getSupervisor(String employeeNumber) {
        return this.employeeMapper.toDto(
                this.employeeRepository.findSupervisorByEmployeeNumber(employeeNumber).orElse(new Employee())
        );
    }
}
