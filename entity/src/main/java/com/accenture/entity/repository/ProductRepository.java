package com.accenture.entity.repository;

import com.accenture.entity.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByProductNumber(String productNumber);

    @Query("SELECT p FROM Product p WHERE p.customer.cif = :cif")
    List<Product> findAllByCif(String cif);

}
