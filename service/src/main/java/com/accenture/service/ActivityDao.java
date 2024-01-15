package com.accenture.service;

import com.accenture.api.dto.OfferDTO;
import com.accenture.api.form.OfferForm;

public interface ActivityDao {

    public OfferDTO createOffer(OfferForm form);

}
