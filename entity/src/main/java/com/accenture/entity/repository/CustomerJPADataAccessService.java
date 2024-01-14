package com.accenture.entity.repository;


import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.entity.mapper.CustomerMapper;
import com.accenture.entity.model.Customer;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.service.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class CustomerJPADataAccessService implements CustomerDao {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final FiltersSpecification<Customer> customerFiltersSpecification;

    @Override
    public List<CustomerDTO> searchCustomers(RequestSearchForm searchForm) {
        return this.customerRepository.findAll(
                        customerFiltersSpecification.getSearchSpecification(
                                searchForm.getSearchRequestDTO(),
                                searchForm.getGlobalOperator()))
                .stream()
                .map(customerMapper::toDto)
                .toList();

    }
    @Override
    public CustomerDTO create(CustomerForm customerForm) {
        return this.customerMapper.toDto(
                this.customerRepository.save(
                        this.customerMapper.toCustomer(customerForm)
                )
        );
    }
}
