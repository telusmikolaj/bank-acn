package com.accenture.entity.model.customer;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_type")
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CustomerTypeName type;

    // Constructors, getters, and setters
}
