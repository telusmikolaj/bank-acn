package com.accenture.entity.model.employee;


import com.accenture.api.form.RoleName;
import com.accenture.entity.model.data.Address;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_number", unique = true)
    private String employeeNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address")
    @Cascade(CascadeType.PERSIST)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @Cascade(CascadeType.PERSIST)
    private Branch branch;

    @Enumerated(EnumType.STRING)
    private RoleName role;


    @ManyToOne
    @JoinColumn(name = "supervisor")
    private Employee supervisor;

}
