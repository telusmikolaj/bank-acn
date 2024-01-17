package com.accenture.service;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    @Override
    public List<EmployeeDTO> search(String searchQuery) {
        return this.employeeDao.search(searchQuery);
    }
}
