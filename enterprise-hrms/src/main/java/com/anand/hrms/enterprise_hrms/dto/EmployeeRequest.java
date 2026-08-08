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
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
}