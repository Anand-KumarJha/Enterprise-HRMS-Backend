package com.anand.hrms.enterprise_hrms.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeRequest {
    @NotBlank
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phone;
    private Long departmentId;
}