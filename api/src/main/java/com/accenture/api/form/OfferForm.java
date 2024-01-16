package com.accenture.api.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class OfferForm extends ActivityForm {

    private ProductType productType;

    private LocalDate validityPeriod;

}
