package com.accenture.entity.model.data;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact_data")
@Data
public class ContactData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

}

