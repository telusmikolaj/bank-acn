package com.accenture.api.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class OfferForm extends ActivityForm {

    private ProductType productType;

    private LocalDate validityPeriod;

}
