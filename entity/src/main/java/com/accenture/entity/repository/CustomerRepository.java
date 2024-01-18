package com.accenture.entity.repository;

import com.accenture.entity.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    Optional<Customer> findCustomerByCustomerNumber(String customerNumber);

    List<Customer> findAllByEmployeeId(Long id);

}
