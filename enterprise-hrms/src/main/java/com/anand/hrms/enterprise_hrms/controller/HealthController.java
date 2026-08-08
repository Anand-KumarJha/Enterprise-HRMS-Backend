package com.anand.hrms.enterprise_hrms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/api/v1/health")
    public Map<String, String> health(){
        return Map.of(
                "status", "UP",
                "application", "Enterprise HRMS",
                "version", "1.0");
    }
}
