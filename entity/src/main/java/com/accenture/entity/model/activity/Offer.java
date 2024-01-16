package com.accenture.entity.model.activity;

import com.accenture.api.form.ProductType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "offer")
@Data
public class Offer extends Activity {

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    @Column(name = "validity_period", nullable = false)
    private LocalDate validityPeriod;

}
