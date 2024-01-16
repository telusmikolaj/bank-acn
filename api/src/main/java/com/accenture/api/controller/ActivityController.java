package com.accenture.api.controller;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.form.ActivityForm;
import com.accenture.api.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ActivityDTO create(@RequestBody ActivityForm activityForm) {
        return this.activityService.create(activityForm);
    }
}
