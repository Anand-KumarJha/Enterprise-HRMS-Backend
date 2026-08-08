package com.anand.hrms.enterprise_hrms.service.impl;

import com.anand.hrms.enterprise_hrms.dto.EmployeeRequest;
import com.anand.hrms.enterprise_hrms.dto.EmployeeResponse;
import com.anand.hrms.enterprise_hrms.entity.Employee;
import com.anand.hrms.enterprise_hrms.exception.EmployeeAlreadyExistsException;
import com.anand.hrms.enterprise_hrms.exception.EmployeeNotFoundException;
import com.anand.hrms.enterprise_hrms.mapper.EmployeeMapper;
import com.anand.hrms.enterprise_hrms.repository.EmployeeRepository;
import com.anand.hrms.enterprise_hrms.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository repo;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repo, EmployeeMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }
    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        if(repo.existsByEmail(employeeRequest.getEmail())){
            throw new EmployeeAlreadyExistsException(
                    "Employee already exists with email: "
                    + employeeRequest.getEmail()
            );
        }

        Employee employee = mapper.toEntity(employeeRequest);
        Employee savedEmployee = repo.save(employee);
        return mapper.toResponse(savedEmployee);
    }

    @Override
    public List<EmployeeResponse> findAllEmployees(){
        List<Employee> allEmployees = repo.findAll();

        return allEmployees
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse findEmployeeById(Long id){
        Employee employee = repo.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with ID " + id + " doesn't exist")
        );
        return mapper.toResponse(employee);
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeReq) {
        Employee employee = repo.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with ID " + id + " doesn't exist")
        );
        if(repo.existsByEmailAndIdNot(employeeReq.getEmail(), id)){
            throw new EmployeeAlreadyExistsException(
                    "Employee already exists with email: "
                            + employeeReq.getEmail()
            );
        }

        employee.setFirstName(employeeReq.getFirstName());
        employee.setLastName(employeeReq.getLastName());
        employee.setEmail(employeeReq.getEmail());
        employee.setPhone(employeeReq.getPhone());

        return mapper.toResponse(employee);
    }

    @Override
    public void deleteEmployeeById(Long id){
        if(repo.findById(id).isEmpty())throw new EmployeeNotFoundException("Employee with ID " + id + " doesn't exist");
        repo.deleteById(id);
    }
}
