package com.anand.hrms.enterprise_hrms.controller;

import com.anand.hrms.enterprise_hrms.dto.DepartmentRequest;
import com.anand.hrms.enterprise_hrms.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {
    private final RedisService redisService;

    public HealthController(RedisService redisService){
        this.redisService = redisService;
    }

    @GetMapping("/api/v1/health")
    public Map<String, String> health(){
        return Map.of(
                "status", "UP",
                "application", "Enterprise HRMS",
                "version", "1.0");
    }

    @PostMapping("/api/v1/redis")
    public String testRedis(){
        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentRequest.setName("new");
        departmentRequest.setDescription("department");

        redisService.set("test:1:1", departmentRequest, 60);
        DepartmentRequest departmentRequest1 = redisService.get("test:1:1", DepartmentRequest.class);

        return departmentRequest1.getName();
    }
}
