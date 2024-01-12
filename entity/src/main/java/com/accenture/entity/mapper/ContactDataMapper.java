package com.accenture.entity.mapper;

import com.accenture.api.dto.ContactDataDTO;
import com.accenture.entity.model.data.ContactData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )

public interface ContactDataMapper {

    ContactDataDTO toDto(ContactData contactData);
}
