package com.anand.hrms.enterprise_hrms.service;

import com.anand.hrms.enterprise_hrms.dto.EmployeeRequest;
import com.anand.hrms.enterprise_hrms.dto.EmployeeResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeResponse saveEmployee(EmployeeRequest employee);

    Page<EmployeeResponse> findAllEmployees(int pageNumber, int pageSize);
    EmployeeResponse findEmployeeById(Long id);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest employee);
    void deleteEmployeeById(Long id);
}
