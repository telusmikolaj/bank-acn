package com.accenture.entity.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id", unique = true)
    private Address address;

}
