package com.accenture.entity.repository;

import com.accenture.entity.model.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findActivitiesByEmployee_Id(Long employeeId);
}
