package com.accenture.service;

import com.accenture.api.dto.CreditDTO;
import com.accenture.api.form.CreditForm;

public interface ProductDao {

    CreditDTO create(CreditForm creditForm);

}
