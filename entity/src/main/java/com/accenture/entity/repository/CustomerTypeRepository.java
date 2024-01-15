package com.accenture.entity.repository;

import com.accenture.api.form.CustomerTypeName;
import com.accenture.entity.model.customer.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, Long> {
    Optional<CustomerType> findByName(CustomerTypeName name);

}
