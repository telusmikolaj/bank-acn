package com.accenture.service;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.form.ActivityForm;

public interface ActivityDao {

    ActivityDTO create(ActivityForm activityForm);
}
