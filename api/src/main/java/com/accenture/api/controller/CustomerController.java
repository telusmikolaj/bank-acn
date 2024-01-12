package com.accenture.api.controller;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.accenture.api.service.CustomerService;

import java.net.URI;
import java.util.Map;

import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<HttpResponse> create(@RequestBody CustomerForm customerForm) {
        CustomerDTO created = this.customerService.create(customerForm);
        return ResponseEntity
                .created(getUri())
                .body(createHttpResponse(CREATED, of("customer", created), "Customer created"));
    }

    @GetMapping({"/{customerNumber}"})
    public ResponseEntity<HttpResponse> get(@PathVariable String customerNumber) {
        CustomerDTO customerDTO = this.customerService.selectByCustomerNumber(customerNumber);
        return ResponseEntity
                .ok()
                .body(createHttpResponse(OK, of("customer", customerDTO), "Customer created"));
    }

    private HttpResponse createHttpResponse(HttpStatus httpStatus, Map<?, ?> data, String message) {
        return HttpResponse.builder()
                .timeStamp(now().toString())
                .data(data)
                .message(message)
                .status(httpStatus)
                .statusCode(httpStatus.value())
                .build();
    }

    private URI getUri() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customer/get/<customerId>").toUriString());
    }
}
