package com.accenture.api.form;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class MeetingForm extends ActivityForm {

    private AddressForm address;
}
