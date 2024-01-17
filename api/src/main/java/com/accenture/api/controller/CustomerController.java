package com.accenture.api.controller;

import com.accenture.api.dto.CustomerDTO;
import com.accenture.api.form.CustomerForm;
import com.accenture.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDTO create(@RequestBody CustomerForm customerForm) {
        return this.customerService.create(customerForm);

    }

    @GetMapping
    public List<CustomerDTO> searchCustomers(@RequestParam String searchQuery) {
        return this.customerService.searchCustomers(searchQuery);

    }

}
