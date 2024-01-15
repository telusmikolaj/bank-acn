package com.accenture.entity.repository;

import com.accenture.api.dto.CreditDTO;
import com.accenture.api.exception.EntityNotFoundException;
import com.accenture.api.form.CreditForm;
import com.accenture.entity.mapper.CreditMapper;
import com.accenture.entity.model.Customer;
import com.accenture.entity.model.product.Credit;
import com.accenture.service.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductJPADataAccessService implements ProductDao {

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    private final CreditMapper creditMapper;

    @Override
    public CreditDTO create(CreditForm creditForm) {
        Credit transientCredit = this.creditMapper.toCredit(creditForm);
        transientCredit.setCustomer(getCustomerById(creditForm.getCustomerId()));

        return this.creditMapper.toDto(
                this.productRepository.save(transientCredit)
        );
    }

    private Customer getCustomerById(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + id + " not found"));
    }

}
