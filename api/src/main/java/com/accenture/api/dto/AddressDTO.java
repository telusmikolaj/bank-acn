package com.accenture.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String province;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String country;
}
