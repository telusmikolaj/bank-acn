package com.accenture.api.form;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OfferForm {

    private Long productId;

    private LocalDate validityPeriod;

    private LocalDateTime date;

    private String status;

    private String description;

    private Long customerId;

    private Long employeeId;
}
