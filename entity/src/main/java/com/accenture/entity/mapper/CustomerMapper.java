package com.accenture.entity.mapper;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.entity.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {ContactDataMapper.class, EmployeeMapper.class, AddressMapper.class,
        ContactDataMapper.class, CustomerTypeMapper.class} )
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);
    Customer toCustomer(CustomerForm customerForm);

}
