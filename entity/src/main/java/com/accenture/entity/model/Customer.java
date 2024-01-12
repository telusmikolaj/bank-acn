package com.accenture.entity.model;

import com.accenture.entity.model.customer.CustomerType;
import com.accenture.entity.model.data.Address;
import com.accenture.entity.model.data.ContactData;
import com.accenture.entity.model.employee.Employee;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "contact_data", referencedColumnName = "id")
    private ContactData contactData;

    @ManyToOne
    @JoinColumn(name = "customer_type", referencedColumnName = "id")
    private CustomerType customerType;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}
