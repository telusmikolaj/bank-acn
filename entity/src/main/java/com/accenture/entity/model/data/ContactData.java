package com.accenture.entity.model.data;


import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class ContactData {

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

}

