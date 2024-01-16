package com.accenture.api.service;

import com.accenture.api.dto.ExposureDTO;
import com.accenture.api.dto.ProductDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getCustomerPortfolio(String cif);

    ExposureDTO getExposure(@PathVariable String cif);
}