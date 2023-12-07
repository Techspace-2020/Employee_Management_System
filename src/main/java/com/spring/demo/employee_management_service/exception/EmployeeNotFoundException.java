package com.spring.demo.employee_management_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Throwable {

    public EmployeeNotFoundException (Integer employeeId){
        super("Employee details not found for the EmployeeID: " + employeeId);
    }
}
