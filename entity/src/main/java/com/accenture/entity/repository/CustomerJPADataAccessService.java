package com.accenture.entity.repository;


import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.exception.EntityNotFoundException;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.CustomerTypeName;
import com.accenture.entity.mapper.CustomerMapper;
import com.accenture.entity.model.Customer;
import com.accenture.entity.model.customer.CustomerType;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.entity.util.QueryParser;
import com.accenture.service.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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

    private final QueryParser queryParser;

    private final FiltersSpecification<Customer> filter;

    @Override
    @Transactional
    public CustomerDTO create(CustomerForm customerForm) {
        Customer transientCustomer = this.customerMapper.toCustomer(customerForm);
        transientCustomer.setEmployee(getEmployeeById(customerForm.getEmployeeId()));
        transientCustomer.setCustomerType(getCustomerTypeByIdOrCreateNew(customerForm.getCustomerType()));

        return this.customerMapper.toDto(
                this.customerRepository.save(transientCustomer)
        );
    }

    @Override
    public List<CustomerDTO> searchCustomers(String searchQuery) {
        Specification<Customer> groupedSearchSpecification
                = this.filter.getGroupedSearchSpecification(this.queryParser.parseSearchString(searchQuery));

        return this.customerRepository.findAll(groupedSearchSpecification)
                .stream()
                .map(this.customerMapper::toDto)
                .toList();

    }

    private Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));
    }

    private CustomerType getCustomerTypeByIdOrCreateNew(CustomerTypeName customerType) {
        return this.customerTypeRepository.findByName(customerType)
                .orElse(CustomerType.builder()
                        .name(customerType).build());
    }

}
