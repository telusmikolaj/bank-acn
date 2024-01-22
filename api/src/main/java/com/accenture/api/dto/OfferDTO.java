package com.accenture.api.dto;

import com.accenture.api.form.ProductType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class OfferDTO extends ActivityDTO {

    private Long id;

    private ProductType productType;

    private LocalDate validityPeriod;

}
