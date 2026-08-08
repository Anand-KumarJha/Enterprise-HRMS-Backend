package com.anand.hrms.enterprise_hrms.mapper;

import com.anand.hrms.enterprise_hrms.dto.DepartmentRequest;
import com.anand.hrms.enterprise_hrms.dto.DepartmentResponse;
import com.anand.hrms.enterprise_hrms.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department toEntity(DepartmentRequest departmentRequest){
        Department department = new Department();

        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());

        return department;
    }

    public DepartmentResponse toResponse(Department department){
        DepartmentResponse response= new DepartmentResponse();

        response.setId(department.getId());
        response.setName(department.getName());
        response.setDescription(department.getDescription());

        return response;
    }
}
