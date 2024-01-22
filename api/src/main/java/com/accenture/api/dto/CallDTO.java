package com.accenture.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CallDTO extends ActivityDTO {

    private LocalDateTime startTime;

    private ContactDataDTO contactData;

}
