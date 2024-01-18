package com.accenture.api.service;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityForm;
import com.accenture.api.form.RequestSearchForm;

import java.util.List;

public interface ActivityService {

    ActivityDTO create(ActivityForm activityForm);
    List<ActivityDTO> search(RequestSearchForm requestSearchForm);

    ActivitySummaryDTO getEmployeeActivitySummary(Long employeeId);

    ActivitySummaryDTO getCustomerActivitySummary(String cif);

    void delete(Long id);

    ActivityDTO update(Long id, ActivityForm form);
}
