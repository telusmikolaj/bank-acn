package com.accenture.entity.mapper;

import com.accenture.api.form.OfferForm;
import com.accenture.entity.model.activity.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    Offer toOffer(OfferForm offerForm);
}
