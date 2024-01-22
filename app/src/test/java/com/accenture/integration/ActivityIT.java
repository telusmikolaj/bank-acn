package com.accenture.integration;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityType;
import com.accenture.api.form.MeetingForm;
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
public class ActivityIT {
    public static final String ACTIVITY_URL_TEMPLATE = "/activity";

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateActivityWhenFormValid() {
        MeetingForm sent = SampleDataFactory.getSampleActvityForm();

        ResponseEntity<ActivityDTO> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE,
                        HttpMethod.POST,
                        new HttpEntity<>(sent),
                        ActivityDTO.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        ActivityDTO returned = response.getBody();
        assertThat(returned.getDate()).isEqualTo(sent.getDate());
        assertThat(returned.getStatus()).isEqualTo(sent.getStatus());
        assertThat(returned.getCustomerNumber()).isEqualTo(sent.getCustomerNumber());
        assertThat(returned.getEmployeeNumber()).isEqualTo(sent.getEmployeeNumber());
        assertThat(returned.getType()).isEqualTo(sent.getType());
    }

    @Test
    void shouldNotCreateActivityWhenFormIsNotValid() {
        MeetingForm invalidForm = MeetingForm.builder()
                .type(ActivityType.MEETING)
                .build();

        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE,
                        HttpMethod.POST,
                        new HttpEntity<>(invalidForm),
                        ProblemDetail.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void shouldNotCreateActivityWhenCustomerNotExists() {
        MeetingForm invalidForm = SampleDataFactory.getSampleActvityForm();
        invalidForm.setCustomerNumber("999");

        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE,
                        HttpMethod.POST,
                        new HttpEntity<>(invalidForm),
                        ProblemDetail.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void shouldNotCreateActivityWhenEmployeeNotExists() {
        MeetingForm invalidForm = SampleDataFactory.getSampleActvityForm();
        invalidForm.setEmployeeNumber("999");

        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE,
                        HttpMethod.POST,
                        new HttpEntity<>(invalidForm),
                        ProblemDetail.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void shouldReturnEmployeeActivitySummaryWhenEmployeeIdValid() {

        ResponseEntity<ActivitySummaryDTO> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE + "/summary/employee/1",
                        HttpMethod.GET,
                        null,
                        ActivitySummaryDTO.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        ActivitySummaryDTO returned = response.getBody();

        assertThat(returned.getActivites().size()).isEqualTo(2);

    }

    @Test
    void shouldNotReturnEmployeeActivitySummaryWhenEmployeeIdIsInValid() {

        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE + "/summary/employee/0",
                        HttpMethod.GET,
                        null,
                        ProblemDetail.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnCustomerActivitySummaryWhenCustomerIdValid() {

        ResponseEntity<ActivitySummaryDTO> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE + "/summary/customer/12345678910",
                        HttpMethod.GET,
                        null,
                        ActivitySummaryDTO.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        ActivitySummaryDTO returned = response.getBody();

        assertThat(returned.getActivites().size()).isEqualTo(2);

    }

    @Test
    void shouldDeleteWithValidId() {

        ResponseEntity<Void> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE + "/1",
                        HttpMethod.DELETE,
                        null,
                        Void.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotDeleteWithInvalidId() {

        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE + "/0",
                        HttpMethod.DELETE,
                        null,
                        ProblemDetail.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldUpdateWhenFormValid() {
        MeetingForm sent = SampleDataFactory.getSampleActvityForm();

        ResponseEntity<ActivityDTO> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE + "/1",
                        HttpMethod.PUT,
                        new HttpEntity<>(sent),
                        ActivityDTO.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        ActivityDTO returned = response.getBody();
        assertThat(returned.getDate()).isEqualTo(sent.getDate());
        assertThat(returned.getStatus()).isEqualTo(sent.getStatus());
        assertThat(returned.getCustomerNumber()).isEqualTo(sent.getCustomerNumber());
        assertThat(returned.getEmployeeNumber()).isEqualTo(sent.getEmployeeNumber());
        assertThat(returned.getType()).isEqualTo(sent.getType());

    }

    @Test
    void shouldNotUpdateWhenActivityIdNotValid() {
        MeetingForm sent = SampleDataFactory.getSampleActvityForm();

        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE + "/0",
                        HttpMethod.PUT,
                        new HttpEntity<>(sent),
                        ProblemDetail.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldNotUpdateWhenActivityFormNotValid() {
        MeetingForm sent = SampleDataFactory.getSampleActvityForm();
        sent.setEmployeeNumber("");
        sent.setCustomerNumber("");

        ResponseEntity<ProblemDetail> response = restTemplate
                .exchange(
                        ACTIVITY_URL_TEMPLATE + "/1",
                        HttpMethod.PUT,
                        new HttpEntity<>(sent),
                        ProblemDetail.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
