package com.anand.hrms.enterprise_hrms.dto;

import com.anand.hrms.enterprise_hrms.entity.Designation;
import lombok.Data;

@Data
public class EmployeeResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Designation designation;
    private String CurrentBand;
}
