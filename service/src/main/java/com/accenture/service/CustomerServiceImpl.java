package com.accenture.service;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Override
    public List<CustomerDTO> searchCustomers(RequestSearchForm requestSearchForm) {
        return this.customerDao.searchCustomers(requestSearchForm);
    }

    @Override
    public CustomerDTO create(CustomerForm customerForm) {
        return this.customerDao.create(customerForm);
    }

    @Override
    public List<CustomerDTO> getPortfolio(Long employeeId) {
        return this.customerDao.getPortfolio(employeeId);
    }
}
