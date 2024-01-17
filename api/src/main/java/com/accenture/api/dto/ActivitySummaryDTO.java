package com.accenture.api.dto;

import com.accenture.api.form.ActivityStatus;
import com.accenture.api.form.ActivityType;
import lombok.Data;

import java.util.Map;

@Data
public class ActivitySummaryDTO {

    Map<ActivityType, Map<ActivityStatus, Long>> activitesMap;

    public ActivitySummaryDTO(Map<ActivityType, Map<ActivityStatus, Long>> activitesMap) {
        this.activitesMap = activitesMap;
    }
}
