package com.accenture.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class SavingAccountDTO extends ProductDTO {

    private BigDecimal interestRate;
}
