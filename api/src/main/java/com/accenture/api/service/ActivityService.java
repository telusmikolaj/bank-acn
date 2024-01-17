package com.accenture.api.service;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityForm;

public interface ActivityService {

    ActivityDTO create(ActivityForm activityForm);

    ActivitySummaryDTO getActivitySummary(Long employeeId);
}
