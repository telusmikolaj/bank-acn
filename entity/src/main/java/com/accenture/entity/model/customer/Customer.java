package com.accenture.entity.model.customer;

import com.accenture.api.form.CustomerTypeName;
import com.accenture.entity.model.data.Address;
import com.accenture.entity.model.data.ContactData;
import com.accenture.entity.model.employee.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_number", unique = true)
    private String customerNumber;

    @Column(name = "cif", unique = true)
    private String cif;

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    private Address address;

    @Embedded
    private ContactData contactData;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerTypeName customerType;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}
