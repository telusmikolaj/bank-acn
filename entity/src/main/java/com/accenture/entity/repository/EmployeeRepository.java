package com.accenture.entity.repository;

import com.accenture.entity.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findEmployeeByEmployeeNumber(String employeeNumber);
    List<Employee> findAllBySupervisorEmployeeNumber(String employeeNumber);
    @Query("SELECT e.supervisor FROM Employee e WHERE e.employeeNumber = :employeeNumber")
    Optional<Employee> findSupervisorByEmployeeNumber(String employeeNumber);
}
