package com.accenture.entity.repository;


import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.exception.ApiException;
import com.accenture.api.exception.CustomDataAccessException;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.entity.mapper.CustomerMapper;
import com.accenture.entity.model.customer.Customer;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.service.CustomerDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomerJPADataAccessService implements CustomerDao {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final EmployeeRepository employeeRepository;


    private final FiltersSpecification<Customer> filter;

    @Override
    public CustomerDTO create(CustomerForm customerForm) {

        try {
            Customer transientCustomer = this.customerMapper.toCustomer(customerForm);
            transientCustomer.setEmployee(getEmployeeById(customerForm.getEmployeeId()));

            return this.customerMapper.toDto(this.customerRepository.save(transientCustomer));
        } catch (DataAccessException e) {
            log.error("Database access error: {}", e.getMessage());
            throw new CustomDataAccessException("Database operation failed " + e.getMessage());
        } catch (Exception e) {
            log.error("General error: {}", e.getMessage());
            throw new ApiException("Error occurred during processing");
        }

    }

    @Override
    public List<CustomerDTO> searchCustomers(RequestSearchForm requestSearchForm) {
        return this.customerRepository.findAll(this.filter.getSearchSpecification(requestSearchForm))
                .stream()
                .map(this.customerMapper::toDto)
                .toList();


    }

    @Override
    public List<CustomerDTO> getPortfolio(Long employeeId) {
        return this.customerRepository.findAllByEmployeeId(employeeId).stream()
                .map(this.customerMapper::toDto)
                .toList();
    }

    private Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));
    }

}
