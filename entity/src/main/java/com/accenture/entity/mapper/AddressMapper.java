package com.accenture.entity.mapper;

import com.accenture.api.dto.AddressDTO;
import com.accenture.entity.model.data.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTO toDto(Address address);
}