package com.accenture.api.form;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingForm extends ActivityForm {

    private AddressForm address;
}
