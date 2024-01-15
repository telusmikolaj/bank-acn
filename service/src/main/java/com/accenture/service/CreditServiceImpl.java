package com.accenture.service;

import com.accenture.api.dto.CreditDTO;
import com.accenture.api.form.CreditForm;
import com.accenture.api.service.CreditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CreditServiceImpl implements CreditService {

    private final ProductDao productDao;

    @Override
    public CreditDTO create(CreditForm creditForm) {

        return this.productDao.create(creditForm);
    }
}
