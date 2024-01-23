package com.accenture.integration;

import com.accenture.api.dto.ExposureDTO;
import com.accenture.api.dto.ProductDTO;
import main.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ProductIT {

    public static final String PRODUCT_URL_TEMPLATE = "/product";
    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReadProduct() {
        ResponseEntity<ProductDTO> response = restTemplate
                .exchange(
                        PRODUCT_URL_TEMPLATE + "/PROD001",
                        HttpMethod.GET,
                        null,
                        ProductDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        ProductDTO returned = response.getBody();
        assertThat(returned.getId()).isEqualTo(1);
        assertThat(returned.getProductNumber()).isEqualTo("PROD001");

    }

    @Test
    void shouldNotReadProductWhenProductNumberNotExists() {
        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        PRODUCT_URL_TEMPLATE + "/0",
                        HttpMethod.GET,
                        null,
                        ProblemDetail.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void shouldReturnPortfolioWhenCifExists() {
        ResponseEntity<ProductDTO[]> response = restTemplate
                .exchange(
                        PRODUCT_URL_TEMPLATE + "/portfolio/12345678910",
                        HttpMethod.GET,
                        null,
                        ProductDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isEqualTo(5);

    }

    @Test
    void shouldNotReturnPortfolioWhenCiNotfExists() {
        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        PRODUCT_URL_TEMPLATE + "/portfolio/0",
                        HttpMethod.GET,
                        null,
                        ProblemDetail.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnExposureWhenCifExists() {
        ResponseEntity<ExposureDTO> response = restTemplate
                .exchange(
                        PRODUCT_URL_TEMPLATE + "/exposure/12345678910",
                        HttpMethod.GET,
                        null,
                        ExposureDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void shouldNotReturnExposureWhenCifNotExists() {
        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        PRODUCT_URL_TEMPLATE + "/exposure/0",
                        HttpMethod.GET,
                        null,
                        ProblemDetail.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

}
