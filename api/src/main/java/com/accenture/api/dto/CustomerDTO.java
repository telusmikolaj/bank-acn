package com.accenture.api.dto;

import lombok.*;

@Data
public class CustomerDTO {
    private Long id;
    private String cif;
    private String firstName;
    private String lastName;
}
