package com.accenture.entity.repository;


import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.service.CustomerDao;
import lombok.RequiredArgsConstructor;
import com.accenture.entity.mapper.CustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class CustomerJPADataAccessService implements CustomerDao {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

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
    public CustomerDTO create(CustomerForm customerForm) {
        return this.customerMapper.toDto(
                this.customerRepository.save(
                        this.customerMapper.toCustomer(customerForm)
                )
        );
    }
}
