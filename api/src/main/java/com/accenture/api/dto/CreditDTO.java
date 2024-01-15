package com.accenture.api.dto;

import com.accenture.api.form.PaymentDetailsForm;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreditDTO extends ProductDTO {

    private LocalDate launchDate;

    private LocalDate dueDate;

    private BigDecimal interestRate;

    private PaymentDetailsForm paymentDetails;

}
