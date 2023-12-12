package com.spring.demo.employee_management_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "Employee_Id")
    private int employeeId;

    @JsonProperty(value = "Employee_name")
    private String employeeName;

    @JsonProperty(value = "Employee_role")
    private String role;

    @JsonProperty(value = "Employee_location")
    private String location;

}
