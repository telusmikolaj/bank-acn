package com.accenture.api.form;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentDetailsForm {

    private LocalDate paymentDate;

    private BigDecimal amount;

    private String status;

    private String paymentType;

    private BigDecimal lateFee;

    private String notes;
}
