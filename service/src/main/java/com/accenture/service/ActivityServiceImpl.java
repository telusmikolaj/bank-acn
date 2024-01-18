package com.accenture.service;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityForm;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityDao activityDao;

    @Override
    public ActivityDTO create(ActivityForm activityForm) {
        return this.activityDao.create(activityForm);
    }

    @Override
    public List<ActivityDTO> search(RequestSearchForm requestSearchForm) {
        return this.activityDao.search(requestSearchForm);
    }

    @Override
    public ActivitySummaryDTO getEmployeeActivitySummary(Long employeeId) {
        return this.activityDao.getActivitySummary(employeeId);
    }

    @Override
    public ActivitySummaryDTO getCustomerActivitySummary(String cif) {
        return this.activityDao.getCustomerActivitySummary(cif);
    }

    @Override
    public void delete(Long id) {
        this.activityDao.delete(id);
    }

    @Override
    public ActivityDTO update(Long id, ActivityForm form) {
        return this.activityDao.update(id, form);
    }
}
