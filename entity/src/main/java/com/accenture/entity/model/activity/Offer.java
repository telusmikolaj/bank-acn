package com.accenture.entity.model.activity;

import com.accenture.entity.model.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "offer")
@Data
public class Offer extends Activity {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "validity_period", nullable = false)
    private LocalDate validityPeriod;

}
