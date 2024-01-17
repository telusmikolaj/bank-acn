package com.accenture.entity.repository;

import com.accenture.entity.model.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {
    List<Activity> findActivitiesByEmployee_Id(Long employeeId);
    List<Activity> findActivitiesByCustomer_Cif(String cif);

}
