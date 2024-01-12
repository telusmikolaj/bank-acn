package com.accenture.service;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Override
    public CustomerDTO create(CustomerForm customerForm) {
        return this.customerDao.create(customerForm);
    }

    @Override
    public List<CustomerDTO> selectAll() {
        return this.customerDao.selectAll();
    }

    @Override
    public CustomerDTO selectById(Long id) {
        return this.customerDao.selectById(id);
    }

    @Override
    public CustomerDTO selectByCustomerNumber(String customerNumber) {
        return this.customerDao.selectByCustomerNumber(customerNumber);
    }
}
