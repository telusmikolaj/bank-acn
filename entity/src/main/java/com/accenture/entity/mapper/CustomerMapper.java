package com.accenture.entity.mapper;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.entity.model.customer.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerDTO toDto(Customer customer);
    Customer toCustomer(CustomerForm customerForm);

}
