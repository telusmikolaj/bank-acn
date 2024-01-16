package com.accenture.entity.mapper;

import com.accenture.api.dto.CreditDTO;
import com.accenture.api.dto.LeasingDTO;
import com.accenture.api.dto.ProductDTO;
import com.accenture.entity.model.product.Credit;
import com.accenture.entity.model.product.Leasing;
import com.accenture.entity.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @SubclassMapping(source = Credit.class, target = CreditDTO.class)
    @SubclassMapping(source = Leasing.class, target = LeasingDTO.class)
    ProductDTO toDto(Product product);



}
