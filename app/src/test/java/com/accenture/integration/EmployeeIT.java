package com.accenture.integration;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.dto.EmployeeDTO;
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
public class EmployeeIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldGetSubordinatesWhenEmployeeNumberExists() {
        ResponseEntity<EmployeeDTO[]> response = restTemplate
                .exchange(
                        "/employee/subordinates/EMP001",
                        HttpMethod.GET,
                        null,
                        EmployeeDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isEqualTo(1);
    }

    @Test
    void shouldNotGetSubordinatesWhenEmployeeNumberNotExists() {
        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        "/employee/subordinates/0",
                        HttpMethod.GET,
                        null,
                        ProblemDetail.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldGetSupervisorWhenEmployeeNumberExists() {
        ResponseEntity<CustomerDTO> response = restTemplate
                .exchange(
                        "/employee/supervisor/EMP002",
                        HttpMethod.GET,
                        null,
                        CustomerDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
    @Test
    void shouldNotGetSupervisorWhenEmployeeNumberNotExists() {
        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        "/employee/supervisor/0",
                        HttpMethod.GET,
                        null,
                        ProblemDetail.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
