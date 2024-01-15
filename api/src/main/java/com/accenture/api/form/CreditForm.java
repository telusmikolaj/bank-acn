package com.accenture.api.form;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreditForm {

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
