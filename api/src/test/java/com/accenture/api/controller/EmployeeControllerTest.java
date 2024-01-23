package com.accenture.api.controller;

import com.accenture.api.dto.EmployeeDTO;
import com.accenture.api.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.SampleDataFactory;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldGetSubordinates() throws Exception {
        String employeeNumber = "EMP001";
        List<EmployeeDTO> expected = SampleDataFactory.getSampleEmployeeDtoList();
        when(employeeService.getSubordinates(employeeNumber)).thenReturn(expected);

        mockMvc.perform(get("/employee/subordinates/" + employeeNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(expected.size())))
                .andExpect(jsonPath("$[0].employeeNumber").value(expected.get(0).getEmployeeNumber()))
                .andExpect(jsonPath("$[1].employeeNumber").value(expected.get(1).getEmployeeNumber()));
    }

    @Test
    void shouldGetSupervisor() throws Exception {
        String employeeNumber = "EMP001";
        EmployeeDTO expected = SampleDataFactory.getSampleEmployeeDto();
        when(employeeService.getSupervisor(employeeNumber)).thenReturn(expected);

        mockMvc.perform(get("/employee/supervisor/" + employeeNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.employeeNumber").value(expected.getEmployeeNumber()));
    }
}