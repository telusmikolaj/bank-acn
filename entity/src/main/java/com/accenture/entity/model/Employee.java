package com.accenture.entity.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeNumber;

    private String firstName;

    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "supervisor")
    private Employee supervisor;

}
