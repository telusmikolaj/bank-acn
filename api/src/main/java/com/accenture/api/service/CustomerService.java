package com.accenture.api.service;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;

import java.util.List;

public interface CustomerService {

    CustomerDTO create(CustomerForm customerForm);

    List<CustomerDTO> selectAll();

    CustomerDTO selectById(Long id);
}
