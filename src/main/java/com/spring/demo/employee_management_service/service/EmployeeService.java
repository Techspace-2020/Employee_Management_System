package com.spring.demo.employee_management_service.service;

import com.spring.demo.employee_management_service.exception.EmployeeNotFoundException;
import com.spring.demo.employee_management_service.model.EmployeeDetails;
import com.spring.demo.employee_management_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public EmployeeDetails addEmployees(EmployeeDetails employeeDetails){
        return employeeRepository.save(employeeDetails);
    }

    public EmployeeDetails getEmployee(int employeeId) throws EmployeeNotFoundException {
        Optional<EmployeeDetails> response=employeeRepository.findById(employeeId);
        if(response.isEmpty()){
            throw new EmployeeNotFoundException(employeeId);
        }
        EmployeeDetails EmployeeDetails= response.get();
        return EmployeeDetails;
    }

    public List<EmployeeDetails> getEmployees(){
        return employeeRepository.findAll();
    }

    public void updateEmployee(int employeeId,EmployeeDetails employeeDetails) throws EmployeeNotFoundException {
        Optional<EmployeeDetails> optional= employeeRepository.findById(employeeId);
        if(optional.isPresent()){
            EmployeeDetails employeeInputDetails = employeeDetails.builder()
                    .employeeName(employeeDetails.getEmployeeName())
                    .location(employeeDetails.getLocation())
                    .role(employeeDetails.getRole())
                    .build();

            employeeRepository.save(employeeInputDetails);
        }else
            throw new EmployeeNotFoundException(employeeId);
    }

    public void deleteEmployee(int employeeId) throws EmployeeNotFoundException {
        Optional<EmployeeDetails> response = employeeRepository.findById(employeeId);
        if(response.isPresent()){
            employeeRepository.deleteById(employeeId);
        }else
            throw new EmployeeNotFoundException(employeeId);

    }
}
