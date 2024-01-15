package com.accenture.entity.repository;


import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.exception.EntityNotFoundException;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.entity.mapper.CustomerMapper;
import com.accenture.entity.model.Customer;
import com.accenture.entity.model.customer.CustomerType;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.service.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerJPADataAccessService implements CustomerDao {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final EmployeeRepository employeeRepository;

    private final CustomerTypeRepository customerTypeRepository;

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
    @Transactional
    public CustomerDTO create(CustomerForm customerForm) {
        Customer transientCustomer = this.customerMapper.toCustomer(customerForm);

        Employee employee = this.employeeRepository.findById(customerForm.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + customerForm.getEmployeeId() + " not found"));
        transientCustomer.setEmployee(employee);

        CustomerType customerType = this.customerTypeRepository.findByName(customerForm.getCustomerType())
                .orElse(CustomerType.builder()
                        .name(customerForm.getCustomerType()).build());

        transientCustomer.setCustomerType(customerType);


        return this.customerMapper.toDto(
                this.customerRepository.save(transientCustomer)
        );
    }

}
