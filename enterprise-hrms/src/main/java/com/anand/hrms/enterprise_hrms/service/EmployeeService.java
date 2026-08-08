package com.anand.hrms.enterprise_hrms.service;

import com.anand.hrms.enterprise_hrms.dto.EmployeeRequest;
import com.anand.hrms.enterprise_hrms.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse saveEmployee(EmployeeRequest employee);

    List<EmployeeResponse> findAllEmployees();
    EmployeeResponse findEmployeeById(Long id);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest employee);
    void deleteEmployeeById(Long id);
}
