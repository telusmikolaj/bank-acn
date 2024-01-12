package com.accenture.service;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;

import java.util.List;

public interface CustomerDao {

    List<CustomerDTO> selectAll();

    CustomerDTO selectById(Long id);

    CustomerDTO create(CustomerForm customerForm);

    CustomerDTO selectByCustomerNumber(String customerNumber);

    List<CustomerDTO> searchCustomers(RequestSearchForm searchForm);
}
