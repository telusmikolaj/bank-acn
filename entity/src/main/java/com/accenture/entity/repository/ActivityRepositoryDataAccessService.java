package com.accenture.entity.repository;

import com.accenture.api.dto.OfferDTO;
import com.accenture.api.exception.EntityNotFoundException;
import com.accenture.api.form.OfferForm;
import com.accenture.entity.mapper.OfferMapper;
import com.accenture.entity.model.Customer;
import com.accenture.entity.model.activity.Offer;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.model.product.Product;
import com.accenture.service.ActivityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ActivityRepositoryDataAccessService implements ActivityDao {

    private final ActivityRepository activityRepository;

    private final EmployeeRepository employeeRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final OfferMapper offerMapper;

    @Override
    public OfferDTO createOffer(OfferForm form) {
        Offer transientOffer = this.offerMapper.toOffer(form);
        transientOffer.setCustomer(getCustomerById(form.getCustomerId()));
        transientOffer.setEmployee(getEmployeeById(form.getEmployeeId()));
        transientOffer.setProduct(getProductById(form.getProductId()));

        return this.offerMapper.toDto(
                this.activityRepository.save(transientOffer)
        );
    }

    private Customer getCustomerById(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + id + " not found"));
    }

    private Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));
    }

    private Product getProductById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));
    }
}
