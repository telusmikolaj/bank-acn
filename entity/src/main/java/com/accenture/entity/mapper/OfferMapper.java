package com.accenture.entity.mapper;

import com.accenture.api.form.OfferForm;
import com.accenture.entity.model.activity.Offer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    Offer toOffer(OfferForm offerForm);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOffer(OfferForm form, @MappingTarget Offer offer);
}
