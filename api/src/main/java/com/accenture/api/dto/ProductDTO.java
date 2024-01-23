package com.accenture.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String productNumber;

    private BigDecimal balance;

    private LocalDate openingDate;

    private String accountNumber;

    private CustomerDTO customer;

    private String type;


}
