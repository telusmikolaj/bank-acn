package com.accenture.api.service;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;

import java.util.List;

public interface CustomerService {

    CustomerDTO create(CustomerForm customerForm);

    List<CustomerDTO> searchCustomers(RequestSearchForm requestSearchForm);

    List<CustomerDTO> getPortfolio(Long employeeId);
}
