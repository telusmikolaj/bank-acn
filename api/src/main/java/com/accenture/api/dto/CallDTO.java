package com.accenture.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CallDTO extends ActivityDTO {

    private LocalDateTime startTime;

    private ContactDataDTO contactData;

}
