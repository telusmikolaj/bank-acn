package com.accenture.integration;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.entity.util.SampleDataFactory;
import main.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CustomerIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateNewCustomer() {
        CustomerForm newCustomer = SampleDataFactory.getSampleCustomerForm();

        ResponseEntity<CustomerDTO> response = restTemplate
                .exchange(
                        "/customer",
                        HttpMethod.POST,
                        new HttpEntity<>(newCustomer),
                        CustomerDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        CustomerDTO savedCustomer = response.getBody();
        assertThat(savedCustomer.getCustomerNumber()).isEqualTo(newCustomer.getCustomerNumber());
        assertThat(savedCustomer.getId()).isNotNull();

    }

    @Test
    void shouldNotCreateCustomerWhenValidationFails() {

        ResponseEntity<CustomerDTO> response = restTemplate
                .exchange(
                        "/customer",
                        HttpMethod.POST,
                        new HttpEntity<>(CustomerForm.builder().build()),
                        CustomerDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldGetPortfolioWhenEmployeeWithCustomerExists() {

        ResponseEntity<CustomerDTO[]> response = restTemplate.exchange("/customer/portfolio/1", HttpMethod.GET, null, CustomerDTO[].class);
        CustomerDTO[] portfolio = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(portfolio.length).isEqualTo(2);

    }

    @Test
    void shouldNotReturnPortfolioWhenEmployeeWithGivenIdNotExists() {

        ResponseEntity<ProblemDetail> response = restTemplate.exchange("/customer/portfolio/999", HttpMethod.GET, null, ProblemDetail.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

//    @Test
//    void shouldSearchCustomer() {
//        RequestSearchForm searchForm = SampleDataFactory.getSampleRequestSearchFormForCustomer();
//
//        ResponseEntity<CustomerDTO[]> response = restTemplate.exchange(
//                "/customer",
//                HttpMethod.GET,
//                new HttpEntity<>(searchForm),
//                CustomerDTO[].class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//    }
}
