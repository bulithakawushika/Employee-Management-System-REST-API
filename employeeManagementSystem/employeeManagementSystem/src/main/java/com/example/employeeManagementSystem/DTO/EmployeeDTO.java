package com.example.employeeManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private int empId;
    private String empName;
    private String empAddress;
    private String empMNumber;
}
