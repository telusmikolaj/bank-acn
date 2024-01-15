package com.accenture.api.controller;

import com.accenture.api.dto.CreditDTO;
import com.accenture.api.form.CreditForm;
import com.accenture.api.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final CreditService creditService;

    @PostMapping("/credit")
    public CreditDTO createCredit(@RequestBody CreditForm creditForm) {
        return this.creditService.create(creditForm);
    }


}
