package com.accenture.api.controller;

import com.accenture.api.dto.ProductDTO;
import com.accenture.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/portfolio/{cif}")
    public List<ProductDTO> getProductPortfolio(@PathVariable String cif) {
        return this.productService.getProductPortfolio(cif);
    }

}
