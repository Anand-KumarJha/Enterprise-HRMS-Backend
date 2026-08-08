package com.anand.hrms.enterprise_hrms.service.impl;

import com.anand.hrms.enterprise_hrms.dto.DepartmentRequest;
import com.anand.hrms.enterprise_hrms.dto.DepartmentResponse;
import com.anand.hrms.enterprise_hrms.entity.Department;
import com.anand.hrms.enterprise_hrms.exception.DepartmentAlreadyExistsException;
import com.anand.hrms.enterprise_hrms.exception.DepartmentNotFoundException;
import com.anand.hrms.enterprise_hrms.mapper.DepartmentMapper;
import com.anand.hrms.enterprise_hrms.repository.DepartmentRepository;
import com.anand.hrms.enterprise_hrms.service.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repo;
    private final DepartmentMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository repo, DepartmentMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public DepartmentResponse addDepartment(DepartmentRequest departmentReq){
        if(repo.existsByName(departmentReq.getName())) {
            throw new DepartmentAlreadyExistsException(
                    "Department Already Exists With Name: "
                            + departmentReq.getName()
            );
        }
        Department department = mapper.toEntity(departmentReq);
        return mapper.toResponse(repo.save(department));
    }

    @Override
    public List<DepartmentResponse> getAllDepartment(){
        return repo
                .findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id){
        Department department = repo.findById(id).orElseThrow(
                () -> new DepartmentNotFoundException("Department with ID " + id + " does not exist")
        );
        return mapper.toResponse(department);
    }

    @Override
    @Transactional
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentReq){
        Department department = repo.findById(id).orElseThrow(
                () -> new DepartmentNotFoundException("Department with ID " + id + " does not exist")
        );

        if(repo.existsByNameAndIdNot(departmentReq.getName(), id)) {
            throw new DepartmentAlreadyExistsException(
                    "Department Already Exists With Name: "
                            + departmentReq.getName()
            );
        }

        department.setName(departmentReq.getName());
        department.setDescription(departmentReq.getDescription());

        return mapper.toResponse(department);
    }

    @Override
    public void deleteDepartmentById(Long id){
        if(repo.findById(id).isEmpty())throw new DepartmentNotFoundException("Department with ID " + id + " does not exist");
        repo.deleteById(id);
    }
}
