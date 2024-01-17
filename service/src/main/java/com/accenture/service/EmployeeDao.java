package com.accenture.service;

import com.accenture.api.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EmployeeDao {

     List<EmployeeDTO> search(@RequestParam String searchQuery);

}
