package com.anand.hrms.enterprise_hrms.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequest {
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
}
