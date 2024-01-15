package com.accenture.entity.mapper;

import com.accenture.api.dto.CreditDTO;
import com.accenture.api.dto.LeasingDTO;
import com.accenture.api.dto.ProductDTO;
import com.accenture.api.dto.SavingAccountDTO;
import com.accenture.entity.model.product.Credit;
import com.accenture.entity.model.product.Leasing;
import com.accenture.entity.model.product.Product;
import com.accenture.entity.model.product.SavingAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    default ProductDTO toDto(Product product) {
        if (product instanceof Credit credit) {
            return toCreditDto(credit);
        } else if (product instanceof Leasing leasing) {
            return toLeasingDto(leasing);
        } else if (product instanceof SavingAccount account) {
            return toSavingAccountDto(account);
        }
        return new ProductDTO();
    }

    @Mapping(target = "launchDate", source = "launchDate")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "interestRate", source = "interestRate")
    CreditDTO toCreditDto(Credit credit);

    @Mapping(target = "launchDate", source = "launchDate")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "interestRate", source = "interestRate")
    LeasingDTO toLeasingDto(Leasing leasing);

    @Mapping(target = "interestRate", source = "interestRate")
    SavingAccountDTO toSavingAccountDto(SavingAccount leasing);


}
