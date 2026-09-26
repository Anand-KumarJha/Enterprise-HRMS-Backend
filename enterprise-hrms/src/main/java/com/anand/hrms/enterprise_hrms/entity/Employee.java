package com.anand.hrms.enterprise_hrms.entity;

import com.anand.hrms.enterprise_hrms.enums.Bands;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    private String lastName;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    @ManyToOne
    private Designation designation;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Enumerated(EnumType.STRING)
    private Bands performanceBand;
    @Positive
    private BigDecimal salary;
    private LocalDate joiningDate;
}
