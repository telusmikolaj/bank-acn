package com.accenture.api.dto;

import com.accenture.api.form.ActivityStatus;
import com.accenture.api.form.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityDTO {

    private LocalDateTime date;

    private ActivityStatus status;

    private String description;

    private String customerNumber;

    private String employeeNumber;

    private ActivityType activityType;
}
