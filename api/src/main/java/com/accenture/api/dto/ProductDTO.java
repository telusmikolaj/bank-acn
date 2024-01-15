package com.accenture.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class ProductDTO {
    private Long id;
    private String productNumber;
    private BigDecimal balance;

    private LocalDate openingDate;

    private String accountNumber;

    private CustomerDTO customer;
}
