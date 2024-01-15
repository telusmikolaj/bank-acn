package com.accenture.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private String name;

    private String description;
}
