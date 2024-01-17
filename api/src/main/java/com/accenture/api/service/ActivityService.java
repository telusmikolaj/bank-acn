package com.accenture.api.service;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityForm;

import java.util.List;

public interface ActivityService {

    ActivityDTO create(ActivityForm activityForm);
    List<ActivityDTO> search(String searchQuery);

    ActivitySummaryDTO getEmployeeActivitySummary(Long employeeId);

    ActivitySummaryDTO getCustomerActivitySummary(String cif);

}
