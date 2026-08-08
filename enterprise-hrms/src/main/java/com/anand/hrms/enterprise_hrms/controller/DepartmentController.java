package com.anand.hrms.enterprise_hrms.controller;

import com.anand.hrms.enterprise_hrms.dto.DepartmentRequest;
import com.anand.hrms.enterprise_hrms.dto.DepartmentResponse;
import com.anand.hrms.enterprise_hrms.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    private final DepartmentService service;
    public DepartmentController(DepartmentService service){this.service = service;}

    @PostMapping
    public ResponseEntity<DepartmentResponse> addDepartment(@Valid @RequestBody DepartmentRequest departmentReq){
        return new ResponseEntity<>(service.addDepartment(departmentReq), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartment(){
        return new ResponseEntity<>(service.getAllDepartment(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id){
        return new ResponseEntity<>(service.getDepartmentById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentRequest departmentReq){
        return new ResponseEntity<>(service.updateDepartment(id, departmentReq), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDepartmentById(@RequestParam Long id){
        service.deleteDepartmentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}