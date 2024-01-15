package com.accenture.entity.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "credit")
@Data
public class Credit extends Product {

    @Column(name = "launch_date", nullable = false)
    private LocalDate launchDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @OneToOne
    @JoinColumn(name = "payment_details_id")
    @Cascade(CascadeType.PERSIST)
    private PaymentDetails paymentDetails;

}
