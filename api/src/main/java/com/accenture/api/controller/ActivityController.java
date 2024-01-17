package com.accenture.api.controller;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityForm;
import com.accenture.api.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ActivityDTO create(@RequestBody ActivityForm activityForm) {
        return this.activityService.create(activityForm);
    }
    @GetMapping
    public List<ActivityDTO> search(@RequestParam String searchQuery) {
        return this.activityService.search(searchQuery);
    }

    @GetMapping("/summary/employee/{employeeId}")
    public ActivitySummaryDTO getEmployeeActivitySummary(@PathVariable Long employeeId) {
        return this.activityService.getEmployeeActivitySummary(employeeId);
    }
    @GetMapping("/summary/customer/{cif}")
    public ActivitySummaryDTO getCustomerActivitySummary(@PathVariable String cif) {
        return this.activityService.getCustomerActivitySummary(cif);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.activityService.delete(id);
    }
}
