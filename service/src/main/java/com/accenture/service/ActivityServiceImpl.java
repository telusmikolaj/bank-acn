package com.accenture.service;

import com.accenture.api.dto.OfferDTO;
import com.accenture.api.form.OfferForm;
import com.accenture.api.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityDao activityDao;
    @Override
    public OfferDTO createOffer(OfferForm form) {
        return this.activityDao.createOffer(form);
    }
}
