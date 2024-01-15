package com.accenture.entity.model;

import com.accenture.entity.model.customer.CustomerType;
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

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    private Address address;

    @OneToOne
    @JoinColumn(name = "contact_data", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    private ContactData contactData;

    @ManyToOne
    @JoinColumn(name = "customer_type", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    private CustomerType customerType;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    private Employee employee;

}
