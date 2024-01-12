package com.accenture.entity.model.product;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment_details")
@Data
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "late_fee", precision = 10, scale = 2)
    private BigDecimal lateFee;

    @Column(name = "notes")
    private String notes;


}
