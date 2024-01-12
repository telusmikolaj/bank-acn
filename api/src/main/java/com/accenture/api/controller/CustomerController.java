package com.accenture.api.controller;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.accenture.api.service.CustomerService;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;

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

    @GetMapping
    public List<CustomerDTO> searchCustomers(@RequestParam String searchQuery) {
        return customerService.searchCustomers(searchQuery);
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
