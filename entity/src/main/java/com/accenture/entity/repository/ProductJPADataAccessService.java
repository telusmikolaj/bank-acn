package com.accenture.entity.repository;

import com.accenture.api.dto.ExposureDTO;
import com.accenture.api.dto.ProductDTO;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.entity.mapper.ProductMapper;
import com.accenture.entity.model.product.Product;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.service.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductJPADataAccessService implements ProductDao {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final FiltersSpecification<Product> specification;

    @Override
    public ProductDTO read(String productNumber) {
        return this.productMapper.toDto(
                this.productRepository.findProductByProductNumber(productNumber)
                        .orElseThrow(() -> new EntityNotFoundException("Product with number" + productNumber + "not found"))
        );
    }

    @Override
    public List<ProductDTO> search(RequestSearchForm requestSearchForm) {

        return this.productRepository.findAll(this.specification.getSearchSpecification(requestSearchForm))
                .stream()
                .map(this.productMapper::toDto)
                .toList();
    }

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
