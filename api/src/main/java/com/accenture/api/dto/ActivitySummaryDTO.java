package com.accenture.api.dto;

import com.accenture.api.form.ActivityStatus;
import com.accenture.api.form.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySummaryDTO {

    Map<ActivityType, Map<ActivityStatus, Long>> activites;

}
