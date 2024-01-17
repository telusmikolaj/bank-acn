package com.accenture.api.controller;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> search(@RequestParam String searchQuery) {
        return this.employeeService.search(searchQuery);
    }

    @GetMapping("/subordinates/{employeeNumber}")
    public List<EmployeeDTO> getSubordinates(@PathVariable String employeeNumber) {
        return this.employeeService.getSubordinates(employeeNumber);
    }

    @GetMapping("/supervisor/{employeeNumber}")
    public EmployeeDTO getSupervisor(@PathVariable String employeeNumber) {
        return this.employeeService.getSupervisor(employeeNumber);
    }


}
