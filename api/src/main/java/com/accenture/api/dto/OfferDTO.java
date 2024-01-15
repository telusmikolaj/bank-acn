package com.accenture.api.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OfferDTO {

    private Long id;

    private LocalDateTime date;

    private String status;

    private String description;

    private CustomerDTO customer;

    private EmployeeDTO employee;

    private ProductDTO product;

    private LocalDate validityPeriod;

}
