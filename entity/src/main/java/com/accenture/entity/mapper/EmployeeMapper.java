package com.accenture.entity.mapper;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.entity.model.employee.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDto(Employee employee);
}
