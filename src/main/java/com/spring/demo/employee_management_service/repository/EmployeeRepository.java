package com.spring.demo.employee_management_service.repository;

import com.spring.demo.employee_management_service.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails,Integer> {

    EmployeeDetails save(EmployeeDetails employeeDetails);
    Optional<EmployeeDetails> findById(int employeeId);

    List<EmployeeDetails> findAll();

    void deleteById(int employeeId);
}
