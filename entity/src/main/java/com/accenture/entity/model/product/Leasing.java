package com.accenture.entity.model.product;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "leasing")
@Data
public class Leasing extends Product {

    @Column(name = "launch_date", nullable = false)
    private LocalDate launchDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
    private BigDecimal interestRate;

    @OneToOne
    @JoinColumn(name = "payment_details_id")
    private PaymentDetails paymentDetails;

}
