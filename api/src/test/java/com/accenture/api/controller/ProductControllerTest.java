package com.accenture.api.controller;

import com.accenture.api.dto.ExposureDTO;
import com.accenture.api.dto.ProductDTO;
import com.accenture.api.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.SampleDataFactory;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    public static final String PRODUCT_URL_TEMPLATE = "/product";
    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldReadWhenProudctNumberValid() throws Exception {
        String productNumber = "PROD001";
        ProductDTO expected = SampleDataFactory.getSampleProductDTO();
        when(this.productService.read(productNumber)).thenReturn(expected);
        this.mockMvc.perform(get(PRODUCT_URL_TEMPLATE + "/{productNumber}", productNumber))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.productNumber").value(expected.getProductNumber()))
                .andExpect(jsonPath("$.balance").value(expected.getBalance()))
                .andExpect(jsonPath("$.accountNumber").value(expected.getAccountNumber()))
                .andExpect(jsonPath("$.customer.id").value(expected.getCustomer().getId()))
                .andExpect(jsonPath("$.type").value(expected.getType()));
    }

    @Test
    void shouldReturnCustomerPortfolio() throws Exception {
        String cif = "123";
        List<ProductDTO> expected = SampleDataFactory.getSampleProductDtoList();
        when(this.productService.getCustomerPortfolio(cif)).thenReturn(expected);
        this.mockMvc.perform(get(PRODUCT_URL_TEMPLATE + "/portfolio/{cif}", cif))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(expected.get(0).getId()))
                .andExpect(jsonPath("$[0].productNumber").value(expected.get(0).getProductNumber()))
                .andExpect(jsonPath("$[0].balance").value(expected.get(0).getBalance()))
                .andExpect(jsonPath("$[0].accountNumber").value(expected.get(0).getAccountNumber()))
                .andExpect(jsonPath("$[0].customer.id").value(expected.get(0).getCustomer().getId()))
                .andExpect(jsonPath("$[0].type").value(expected.get(0).getType()));
    }

    @Test
    void shouldReturnCustomerExposure() throws Exception {
        String cif = "123";
        ExposureDTO expected = SampleDataFactory.getSampleExposureDto();
        when(this.productService.getExposure(cif)).thenReturn(expected);
        this.mockMvc.perform(get(PRODUCT_URL_TEMPLATE + "/exposure/{cif}", cif))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.exposures.LEASING.count").value(1));
    }
}