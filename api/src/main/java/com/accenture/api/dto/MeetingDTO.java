package com.accenture.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MeetingDTO extends ActivityDTO{

    private AddressDTO address;
}
