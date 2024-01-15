package com.accenture.api.service;

import com.accenture.api.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProductPortfolio(String cif);
}
