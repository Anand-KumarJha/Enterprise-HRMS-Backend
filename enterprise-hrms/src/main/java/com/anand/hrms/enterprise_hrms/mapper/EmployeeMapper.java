package com.anand.hrms.enterprise_hrms.mapper;

import com.anand.hrms.enterprise_hrms.dto.EmployeeRequest;
import com.anand.hrms.enterprise_hrms.dto.EmployeeResponse;
import com.anand.hrms.enterprise_hrms.entity.Employee;
import com.anand.hrms.enterprise_hrms.enums.Bands;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse.setId(employee.getId());
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setDesignation(employee.getDesignation());
//        employeeResponse.setCurrentBand(employee.getPerformanceBand().toString());
        employeeResponse.setCurrentBand(employee.getPerformanceBand() == null ? null : employee.getPerformanceBand().name());
        employeeResponse.setPhone(employee.getPhone());

        return employeeResponse;
    }

    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());

        //default
        employee.setPerformanceBand(Bands.B);

        return employee;
    }
}
