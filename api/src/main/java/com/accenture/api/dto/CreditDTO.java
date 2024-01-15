package com.accenture.api.dto;

import com.accenture.api.form.PaymentDetailsForm;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CreditDTO {

    private LocalDate launchDate;

    private LocalDate dueDate;

    private BigDecimal interestRate;

    private PaymentDetailsForm paymentDetails;

    private String productNumber;

    private BigDecimal balance;

    private LocalDate openingDate;

    private String accountNumber;

    private Long customerId;
}
