package com.accenture.service;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.form.ActivityForm;
import com.accenture.api.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityDao activityDao;

    @Override
    public ActivityDTO create(ActivityForm activityForm) {
        return this.activityDao.create(activityForm);
    }
}
