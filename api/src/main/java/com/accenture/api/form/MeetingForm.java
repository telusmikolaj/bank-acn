package com.accenture.api.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MeetingForm extends ActivityForm {

    private AddressForm address;
}
