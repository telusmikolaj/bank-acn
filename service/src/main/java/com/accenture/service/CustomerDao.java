package com.accenture.service;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;

import java.util.List;

public interface CustomerDao {


    CustomerDTO create(CustomerForm customerForm);

    List<CustomerDTO> searchCustomers(String searchQuery);

    List<CustomerDTO> getPortfolio(Long employeeId);

}
