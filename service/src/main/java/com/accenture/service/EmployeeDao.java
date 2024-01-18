package com.accenture.service;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.api.form.RequestSearchForm;

import java.util.List;

public interface EmployeeDao {

    List<EmployeeDTO> search(RequestSearchForm requestSearchForm);

    List<EmployeeDTO> getSubordinates(String employeeNumber);

    EmployeeDTO getSupervisor(String employeeNumber);

}
