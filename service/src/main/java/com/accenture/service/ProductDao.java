package com.accenture.service;

import com.accenture.api.dto.ProductDTO;

import java.util.List;

public interface ProductDao {

    List<ProductDTO> createProductPortfolio(String cif);

}
