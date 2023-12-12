package com.spring.demo.employee_management_service.controller;


import com.spring.demo.employee_management_service.exception.EmployeeNotFoundException;
import com.spring.demo.employee_management_service.model.EmployeeDetails;
import com.spring.demo.employee_management_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final static Logger logger = Logger.getLogger(EmployeeController.class.getName());

    @RequestMapping(value = "/api/addemployee",method= RequestMethod.POST)
    public ResponseEntity<Object> addEmployees(@RequestBody EmployeeDetails employeeDetails){
        employeeService.addEmployees(employeeDetails);
        return new ResponseEntity<>("Employee Detail Added Successfully!!", HttpStatus.CREATED);
    }

    @RequestMapping(value = "api/getemployee/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployee(@PathVariable int employeeId) throws EmployeeNotFoundException {
        logger.info("Employee Id received:"+ employeeId);
        EmployeeDetails employeeDetails = new EmployeeDetails();

        try {
            employeeDetails = employeeService.getEmployee(employeeId);
        }catch (EmployeeNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeDetails,HttpStatus.OK);
    }

    @RequestMapping(value="/api/getemployees",method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDetails>> getEmployees(){
        List<EmployeeDetails> employeeDetails = employeeService.getEmployees();
        return new ResponseEntity<>(employeeDetails,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/updateemployee/{employeeId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeDetails employeeDetails) throws EmployeeNotFoundException {
        logger.info("Employee Id received:"+ employeeId);
        try {
            employeeDetails.setEmployeeId(employeeId);
            employeeService.updateEmployee(employeeDetails);

        } catch (EmployeeNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Employee Detail updated Successfully!!",HttpStatus.OK);
    }

    @RequestMapping(value = "/api/deleteemployee/{employeeId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) throws EmployeeNotFoundException {
        logger.info("Employee Id received:"+ employeeId);
        try {
            employeeService.deleteEmployee(employeeId);
        } catch (EmployeeNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Employee detail Deleted Successfully!!", HttpStatus.OK);
    }
}
