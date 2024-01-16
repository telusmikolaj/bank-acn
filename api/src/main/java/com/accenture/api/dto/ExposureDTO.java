package com.accenture.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class ExposureDTO {
    private Map<String, ProductExposureInfo> exposures = new HashMap<>();

    public void addProductInfo(ProductDTO productDTO) {
        ProductExposureInfo info = exposures.computeIfAbsent(productDTO.getType(), k -> new ProductExposureInfo());
        info.incrementCount();
        info.addToSum(productDTO.getBalance());
    }

    @Data
    private static class ProductExposureInfo {
        private int count;
        private BigDecimal sum;

        private ProductExposureInfo() {
            this.count = 0;
            this.sum = BigDecimal.ZERO;
        }
        private void incrementCount() {
            this.count++;
        }

        private void addToSum(BigDecimal value) {
            this.sum = this.sum.add(value);
        }

    }
}
