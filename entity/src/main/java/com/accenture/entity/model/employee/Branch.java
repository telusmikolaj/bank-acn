package com.accenture.entity.model.employee;

import com.accenture.entity.model.data.Address;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "branch")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id", unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Address address;

}
