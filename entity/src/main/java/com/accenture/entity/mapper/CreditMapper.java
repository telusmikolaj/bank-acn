package com.accenture.entity.mapper;

import com.accenture.api.dto.CreditDTO;
import com.accenture.api.form.CreditForm;
import com.accenture.entity.model.product.Credit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    Credit toCredit(CreditForm creditForm);

    CreditDTO toDto(Credit creditForm);

}
