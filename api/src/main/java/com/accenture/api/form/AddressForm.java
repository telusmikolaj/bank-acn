package com.accenture.api.form;

import lombok.Data;

@Data
public class AddressForm {

    private String street;

    private String city;

    private String province;

    private String postalCode;

    private String country;

}
