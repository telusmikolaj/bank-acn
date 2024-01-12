package com.accenture.service;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;

import java.util.List;

public interface CustomerDao {

    List<CustomerDTO> selectAll();

    CustomerDTO selectById(Long id);

    CustomerDTO create(CustomerForm customerForm);

    CustomerDTO selectByCustomerNumber(String customerNumber);

    List<CustomerDTO> searchCustomers(String searchQuery);
}
