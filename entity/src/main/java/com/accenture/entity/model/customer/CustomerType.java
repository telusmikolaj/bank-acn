package com.accenture.entity.model.customer;

import com.accenture.api.form.CustomerTypeName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CustomerTypeName name;

}
