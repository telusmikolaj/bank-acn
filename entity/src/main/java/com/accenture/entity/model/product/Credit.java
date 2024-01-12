package com.accenture.entity.model.product;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credit")
public class Credit extends Product {

    @Column(name = "launch_date", nullable = false)
    private LocalDate launchDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @OneToOne
    @JoinColumn(name = "payment_details_id")
    private PaymentDetails paymentDetails;

}
