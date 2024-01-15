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
        Customer customer = this.customerRepository.findById(creditForm.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + creditForm.getCustomerId() + " not found"));
        transientCredit.setCustomer(customer);

        return this.creditMapper.toDto(
                this.productRepository.save(transientCredit)
        );
    }

}
