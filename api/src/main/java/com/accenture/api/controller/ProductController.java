package com.accenture.api.controller;

import com.accenture.api.dto.ExposureDTO;
import com.accenture.api.dto.ProductDTO;
import com.accenture.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productNumber}")
    public ProductDTO read(@PathVariable String productNumber) {
        return this.productService.read(productNumber);
    }
    @GetMapping("/search")
    public List<ProductDTO> search(@RequestParam String query) {
        return this.productService.search(query);
    }
    @GetMapping("/portfolio/{cif}")
    public List<ProductDTO> getCustomerPortfolio(@PathVariable String cif) {
        return this.productService.getCustomerPortfolio(cif);
    }
    @GetMapping("/exposure/{cif}")
    public ExposureDTO getExposure(@PathVariable String cif) {
        return this.productService.getExposure(cif);
    }

}
