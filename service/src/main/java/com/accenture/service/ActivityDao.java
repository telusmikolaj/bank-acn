package com.accenture.service;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityForm;

import java.util.List;

public interface ActivityDao {

    ActivityDTO create(ActivityForm activityForm);

    List<ActivityDTO> search(String searchQuery);

    ActivitySummaryDTO getActivitySummary(Long employeeId);

    ActivitySummaryDTO getCustomerActivitySummary(String cif);

    void delete(Long id);

}
