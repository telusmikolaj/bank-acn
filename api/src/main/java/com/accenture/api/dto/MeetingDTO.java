package com.accenture.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class MeetingDTO extends ActivityDTO{

    private AddressDTO address;
}
