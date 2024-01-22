package com.accenture.api.dto;

import com.accenture.api.form.ActivityStatus;
import com.accenture.api.form.ActivityType;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ActivitySummaryDTO {

    Map<ActivityType, Map<ActivityStatus, Long>> activites;

    public ActivitySummaryDTO(Map<ActivityType, Map<ActivityStatus, Long>> activitesMap) {
        this.activites = activitesMap;
    }
}
