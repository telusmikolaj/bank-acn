package com.accenture.api.service;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.api.form.RequestSearchForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<EmployeeDTO> search(RequestSearchForm requestSearchForm);

    List<EmployeeDTO> getSubordinates(String employeeNumber);

    EmployeeDTO getSupervisor(String employeeNumber);
}
