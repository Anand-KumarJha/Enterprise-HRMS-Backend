package com.anand.hrms.enterprise_hrms.service;

import com.anand.hrms.enterprise_hrms.dto.DepartmentRequest;
import com.anand.hrms.enterprise_hrms.dto.DepartmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse addDepartment(DepartmentRequest departmentReq);
    List<DepartmentResponse> getAllDepartment();
    DepartmentResponse getDepartmentById(Long id);
    DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest);
    void deleteDepartmentById(Long id);
}
