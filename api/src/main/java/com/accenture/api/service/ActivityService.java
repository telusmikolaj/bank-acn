package com.accenture.api.service;

import com.accenture.api.dto.OfferDTO;
import com.accenture.api.form.OfferForm;

public interface ActivityService {


    public OfferDTO createOffer(OfferForm form);
}
