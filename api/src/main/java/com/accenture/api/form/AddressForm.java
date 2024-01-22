package com.accenture.api.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressForm {

    @NotBlank(message = "Street cannot be empty")
    private String street;

    @NotBlank(message = "City cannot be empty")
    private String city;

    private String province;

    @NotBlank(message = "Postal code cannot be empty")
    private String postalCode;

    @NotBlank(message = "Country cannot be empty")
    private String country;

}
