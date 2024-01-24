package com.accenture.entity.repository;

import com.accenture.api.dto.ExposureDTO;
import com.accenture.api.dto.ProductDTO;
import com.accenture.entity.mapper.ProductMapper;
import com.accenture.entity.model.product.Product;
import com.accenture.entity.specification.FiltersSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class ProductJPADataAccessServiceTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");
    private final FiltersSpecification<Product> filtersSpecification = new FiltersSpecification<>();

    @Autowired
    private ProductRepository productRepository;
    @Spy
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @InjectMocks
    private ProductJPADataAccessService underTest;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        this.underTest = new ProductJPADataAccessService(productRepository, productMapper, filtersSpecification, customerRepository);
    }

    @Test
    void shouldReadProductWhenProductNumberValid() {
        String existingProductNumber = "PROD001";
        ProductDTO returned = this.underTest.read(existingProductNumber);
        assertThat(returned).isNotNull();
    }

    @Test
    void shouldNotReadProductWhenProductNumberInvalid() {
        String invalidProductNumber = "0";
        assertThatThrownBy(() -> this.underTest.read(invalidProductNumber)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldReturnCustomerPortfolioWhenCifValid() {
        String validCif = "12345678910";
        when(this.customerRepository.existsCustomerByCif(validCif)).thenReturn(true);
        List<ProductDTO> customerPortfolio = this.underTest.getCustomerPortfolio(validCif);
        assertThat(customerPortfolio).isNotNull();
        assertThat(customerPortfolio).hasSize(5);
    }

    @Test
    void shouldNotReturnCustomerPortfolioWhenCifInalid() {
        String invalidCif = "0";
        assertThatThrownBy(() -> this.underTest.read(invalidCif)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldReturnCustomerExposureWhenCifValid() {
        String validCif = "12345678910";
        when(this.customerRepository.existsCustomerByCif(validCif)).thenReturn(true);
        ExposureDTO exposure = this.underTest.getExposure(validCif);
        assertThat(exposure).isNotNull();
    }

    @Test
    void shouldNotReturnCustomerExposureWhenCifInvalid() {
        String invalidCif = "0";
        assertThatThrownBy(() -> this.underTest.read(invalidCif)).isInstanceOf(EntityNotFoundException.class);
    }

}