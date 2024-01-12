package com.accenture.entity.mapper;

import com.accenture.api.dto.CustomerTypeDTO;
import com.accenture.entity.model.customer.CustomerType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerTypeMapper {

    CustomerTypeDTO toDto(CustomerType customerType);
}
