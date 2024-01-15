package com.accenture.api.service;

import com.accenture.api.dto.CreditDTO;
import com.accenture.api.form.CreditForm;

public interface CreditService {

    CreditDTO create(CreditForm creditForm);
}
