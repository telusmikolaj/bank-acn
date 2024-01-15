package com.accenture.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {


    private String street;

    private String city;

    private String province;

    private String postalCode;

    private String country;
}
