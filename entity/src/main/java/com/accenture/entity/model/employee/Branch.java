package com.accenture.entity.model.employee;

import com.accenture.entity.model.data.Address;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "branch")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id", unique = true)
    private Address address;

}
