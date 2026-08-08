package com.anand.hrms.enterprise_hrms.controller;

import com.anand.hrms.enterprise_hrms.dto.EmployeeRequest;
import com.anand.hrms.enterprise_hrms.dto.EmployeeResponse;
import com.anand.hrms.enterprise_hrms.entity.Employee;
import com.anand.hrms.enterprise_hrms.service.EmployeeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> saveEmployee(@Valid  @RequestBody EmployeeRequest employee){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.saveEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAllEmployees(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid  @RequestBody EmployeeRequest employee){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateEmployee(id, employee));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployeeById(@RequestParam Long id){
        service.deleteEmployeeById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }
}
