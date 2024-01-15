package com.accenture.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerTypeDTO {

    private Long id;
    private String name;
}
