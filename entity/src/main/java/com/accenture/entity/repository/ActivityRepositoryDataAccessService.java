package com.accenture.entity.repository;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.exception.EntityNotFoundException;
import com.accenture.api.form.ActivityForm;
import com.accenture.entity.mapper.ActivityAbstractMapper;
import com.accenture.entity.mapper.ActivityMapper;
import com.accenture.entity.model.Customer;
import com.accenture.entity.model.activity.Activity;
import com.accenture.entity.model.employee.Employee;
import com.accenture.service.ActivityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ActivityRepositoryDataAccessService implements ActivityDao {

    public static final String NOT_FOUND = " not found";
    private final ActivityRepository activityRepository;

    private final EmployeeRepository employeeRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final ActivityMapper activityMapper;

    private final ActivityAbstractMapper activityAbstractMapper;

    @Override
    public ActivityDTO create(ActivityForm activityForm) {
        Activity activity = this.activityAbstractMapper.toActivity(activityForm);
        activity.setCustomer(getCustomerByNumber(activityForm.getCustomerNumber()));
        activity.setEmployee(getEmployeeByNumber(activityForm.getEmployeeNumber()));
        return this.activityMapper.toDto(this.activityRepository.save(activity));
    }

    private Customer getCustomerByNumber(String customerNumber) {
        return this.customerRepository.findCustomerByCustomerNumber(customerNumber)
                .orElseThrow(() -> new EntityNotFoundException("Customer with number " + customerNumber + " " + NOT_FOUND));
    }

    private Employee getEmployeeByNumber(String employeeNumber) {
        return this.employeeRepository.findEmployeeByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + employeeNumber + " " + NOT_FOUND));
    }


}
