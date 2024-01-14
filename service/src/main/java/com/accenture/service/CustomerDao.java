package com.accenture.service;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;

import java.util.List;

public interface CustomerDao {


    CustomerDTO create(CustomerForm customerForm);

    List<CustomerDTO> searchCustomers(RequestSearchForm searchForm);
}
