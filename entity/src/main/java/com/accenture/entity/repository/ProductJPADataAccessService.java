package com.accenture.entity.repository;

import com.accenture.api.dto.ExposureDTO;
import com.accenture.api.dto.ProductDTO;
import com.accenture.entity.mapper.ProductMapper;
import com.accenture.service.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductJPADataAccessService implements ProductDao {

    private final ProductRepository productRepository;


    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> getCustomerPortfolio(String cif) {
        return this.productRepository.findAllByCif(cif)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ExposureDTO getExposure(String cif) {
        ExposureDTO exposureDTO = new ExposureDTO();
        getCustomerPortfolio(cif).forEach(exposureDTO::addProductInfo);
        return exposureDTO;

    }
}
