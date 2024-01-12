package com.accenture.entity.model.customer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer_type")
@Data
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CustomerTypeName name;

}
