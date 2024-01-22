package com.accenture.api.controller;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import util.SampleDataFactory;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    public static final String CUSTOMER_URL_TEMPLATE = "/customer";
    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldReturnCustomerDto() throws Exception {
        //given
        CustomerForm customerForm = SampleDataFactory.getSampleCustomerForm();
        CustomerDTO customerDto = SampleDataFactory.getSampleCustomerDto();

        //when
        when(customerService.create(customerForm)).thenReturn(customerDto);

        //then
        this.mockMvc.perform(post(CUSTOMER_URL_TEMPLATE)
                        .content(asJsonString(customerForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerNumber").value(customerDto.getCustomerNumber()))
                .andExpect(jsonPath("$.address.street").value(customerDto.getAddress().getStreet()))
                .andExpect(jsonPath("$.contactData.phone").value(customerDto.getContactData().getPhone()))
                .andExpect(jsonPath("$.customerType").value(customerDto.getCustomerType().getCode()))
                .andExpect(jsonPath("$.employee.id").value(customerDto.getEmployee().getId()))
                .andExpect(jsonPath("$.cif").value(customerDto.getCif()));


    }

    @Test
    void invalidFormPostShouldThrowException() throws Exception {
        //given
        CustomerForm customerForm = CustomerForm.builder().build();

        //then
        this.mockMvc.perform(post(CUSTOMER_URL_TEMPLATE)
                        .content(asJsonString(customerForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));


    }

    @Test
    void getPortfolioShouldReturnList() throws Exception {
        //given
        Long employeeId = 1L;
        List<CustomerDTO> expectedPortfolio = SampleDataFactory.getSampleCustomerDtoList();

        //when
        when(customerService.getPortfolio(employeeId)).thenReturn(expectedPortfolio);

        //then
        mockMvc.perform(get(CUSTOMER_URL_TEMPLATE + "/portfolio/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(expectedPortfolio.size())))
                .andExpect(jsonPath("$[0].customerNumber").value(expectedPortfolio.get(0).getCustomerNumber()))
                .andExpect(jsonPath("$[1].customerNumber").value(expectedPortfolio.get(1).getCustomerNumber()))
                .andExpect(jsonPath("$[2].customerNumber").value(expectedPortfolio.get(2).getCustomerNumber()));
    }

    @Test
    void searchCustomerShouldReturnList() throws Exception {
        //given
        List<CustomerDTO> expectedList = SampleDataFactory.getSampleCustomerDtoList();
        RequestSearchForm searchForm = SampleDataFactory.getSampleRequestSearchForm();
        //when
        when(customerService.searchCustomers(any(RequestSearchForm.class))).thenReturn(expectedList);

        //then
        mockMvc.perform(get(CUSTOMER_URL_TEMPLATE)
                        .content(asJsonString(searchForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(expectedList.size())))
                .andExpect(jsonPath("$[0].customerNumber").value(expectedList.get(0).getCustomerNumber()))
                .andExpect(jsonPath("$[1].customerNumber").value(expectedList.get(1).getCustomerNumber()))
                .andExpect(jsonPath("$[2].customerNumber").value(expectedList.get(2).getCustomerNumber()));
    }


    private String asJsonString(Object object) throws JsonProcessingException {
        return this.mapper.writeValueAsString(object);
    }


}