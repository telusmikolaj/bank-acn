package com.accenture.service;

import com.accenture.api.dto.ExposureDTO;
import com.accenture.api.dto.ProductDTO;
import com.accenture.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public List<ProductDTO> getCustomerPortfolio(String cif) {
        return this.productDao.getCustomerPortfolio(cif);
    }

    @Override
    public ExposureDTO getExposure(String cif) {
        return this.productDao.getExposure(cif);
    }

}