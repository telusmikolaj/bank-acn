package com.accenture.entity.repository;


import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.entity.model.Customer;
import com.accenture.entity.specification.CustomerSpecificationBuilder;
import com.accenture.service.CustomerDao;
import lombok.RequiredArgsConstructor;
import com.accenture.entity.mapper.CustomerMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@RequiredArgsConstructor
public class CustomerJPADataAccessService implements CustomerDao {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> searchCustomers(String searchQuery) {
        CustomerSpecificationBuilder builder = new CustomerSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(searchQuery + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), "", "");
        }

        Specification<Customer> spec = builder.build();
        return this.customerRepository.findAll(spec)
                .stream()
                .map(customerMapper::toDto)
                .toList();

    }
    @Override
    public List<CustomerDTO> selectAll() {

        return this.customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    @Override
    public CustomerDTO selectById(Long id) {
        return this.customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public CustomerDTO selectByCustomerNumber(String customerNumber) {
        return this.customerRepository.findCustomerByCustomerNumber(customerNumber)
                .map(customerMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
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
