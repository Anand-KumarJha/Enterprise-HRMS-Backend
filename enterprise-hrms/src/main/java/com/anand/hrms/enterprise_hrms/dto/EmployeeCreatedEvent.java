package com.anand.hrms.enterprise_hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreatedEvent {
    private Long id;
//    private String type;
//    private String name;
    private String email;
}
