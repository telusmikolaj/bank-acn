package com.accenture.entity.model.product;

import com.accenture.api.form.ProductType;
import com.accenture.entity.model.Customer;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product")
@Data
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_number")
    private String productNumber;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;
    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;



}
