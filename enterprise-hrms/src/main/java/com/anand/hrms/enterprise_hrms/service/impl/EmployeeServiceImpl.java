package com.anand.hrms.enterprise_hrms.service.impl;

import com.anand.hrms.enterprise_hrms.dto.EmployeeCreatedEvent;
import com.anand.hrms.enterprise_hrms.dto.EmployeeRequest;
import com.anand.hrms.enterprise_hrms.dto.EmployeeResponse;
import com.anand.hrms.enterprise_hrms.entity.Employee;
import com.anand.hrms.enterprise_hrms.exception.DepartmentNotFoundException;
import com.anand.hrms.enterprise_hrms.exception.EmployeeAlreadyExistsException;
import com.anand.hrms.enterprise_hrms.exception.EmployeeNotFoundException;
import com.anand.hrms.enterprise_hrms.mapper.EmployeeMapper;
import com.anand.hrms.enterprise_hrms.repository.DepartmentRepository;
import com.anand.hrms.enterprise_hrms.repository.EmployeeRepository;
import com.anand.hrms.enterprise_hrms.service.EmployeeService;
import com.anand.hrms.enterprise_hrms.service.KafkaProducerService;
import com.anand.hrms.enterprise_hrms.service.RedisService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository repo;
    private final DepartmentRepository departmentRepo;
    private final EmployeeMapper mapper;
    private final RedisService redisService;
    private final KafkaProducerService kafkaProducerService;

    public EmployeeServiceImpl(EmployeeRepository repo, DepartmentRepository departmentRepo, EmployeeMapper mapper, RedisService redisService, KafkaProducerService kafkaProducerService){
        this.repo = repo;
        this.mapper = mapper;
        this.departmentRepo = departmentRepo;
        this.redisService = redisService;
        this.kafkaProducerService = kafkaProducerService;
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
        employee.setDepartment(
                departmentRepo.findById(
                        employeeRequest.getDepartmentId()).orElseThrow(
                                () -> new DepartmentNotFoundException(
                                        "Department with id " + employeeRequest.getDepartmentId() + " not exist")));

        Employee savedEmployee = repo.save(employee);

        EmployeeCreatedEvent event = new EmployeeCreatedEvent(savedEmployee.getId(), savedEmployee.getEmail());
        kafkaProducerService.sendEmployeeCreatedEvent(event);

        return mapper.toResponse(savedEmployee);
    }

    @Override
    public Page<EmployeeResponse> findAllEmployees(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> allEmployees = repo.findAll(pageable);

        return allEmployees
                .map(mapper::toResponse);
    }

    @Override
    public EmployeeResponse findEmployeeById(Long id){
        String key = "Employee:" + id;
        EmployeeResponse cacheResponse = redisService.get(key, EmployeeResponse.class);
        if(cacheResponse != null)return cacheResponse;

        Employee employee = repo.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with ID " + id + " doesn't exist")
        );
        EmployeeResponse dbResponse = mapper.toResponse(employee);
        redisService.set(key, dbResponse, 300);
        return dbResponse;
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = repo.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee with ID " + id + " doesn't exist")
        );
        if(repo.existsByEmailAndIdNot(employeeRequest.getEmail(), id)){
            throw new EmployeeAlreadyExistsException(
                    "Employee already exists with email: "
                            + employeeRequest.getEmail()
            );
        }

        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setDepartment(
                departmentRepo.findById(
                        employeeRequest.getDepartmentId()).orElseThrow(
                        () -> new DepartmentNotFoundException(
                                "Department with id " + employeeRequest.getDepartmentId() + " not exist")));


        redisService.delete("Employee:"+id);
        return mapper.toResponse(employee);
    }

    @Override
    public void deleteEmployeeById(Long id){
        if(repo.findById(id).isEmpty())throw new EmployeeNotFoundException("Employee with ID " + id + " doesn't exist");
        repo.deleteById(id);
        redisService.delete("Employee:"+id);
    }
}
