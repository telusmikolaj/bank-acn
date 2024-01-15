package com.accenture.api.controller;

import com.accenture.api.dto.OfferDTO;
import com.accenture.api.form.OfferForm;
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

    @PostMapping("/activity/offer")
    public OfferDTO createOffer(@RequestBody OfferForm offerForm) {
        return this.activityService.createOffer(offerForm);
    }
}
