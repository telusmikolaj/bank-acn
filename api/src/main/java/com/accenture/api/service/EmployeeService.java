package com.accenture.api.service;

import com.accenture.api.dto.EmployeeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface EmployeeService {

    List<EmployeeDTO> search(@RequestParam String searchQuery);

    List<EmployeeDTO> getSubordinates(String employeeNumber);

    EmployeeDTO getSupervisor(String employeeNumber);
}
