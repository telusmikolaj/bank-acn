package com.accenture.entity.repository;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.entity.mapper.EmployeeMapper;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.entity.util.QueryParser;
import com.accenture.service.EmployeeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeJPADataAccessService implements EmployeeDao {

    private final EmployeeRepository employeeRepository;

    private final FiltersSpecification<Employee> filter;

    private final QueryParser queryParser;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> search(String searchQuery) {
        Specification<Employee> groupedSearchSpecification
                = this.filter.getGroupedSearchSpecification(this.queryParser.parseSearchString(searchQuery));

        return this.employeeRepository.findAll(groupedSearchSpecification)
                .stream()
                .map(this.employeeMapper::toDto)
                .toList();
    }
}
