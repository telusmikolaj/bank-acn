package com.accenture.api.dto;

import com.accenture.api.form.ActivityStatus;
import com.accenture.api.form.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

    private LocalDateTime date;

    private ActivityStatus status;

    private String description;

    private String customerNumber;

    private String employeeNumber;

    private ActivityType type;
}
