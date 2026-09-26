package com.anand.hrms.enterprise_hrms.service;

import com.anand.hrms.enterprise_hrms.dto.EmployeeCreatedEvent;

public interface KafkaProducerService {
    void sendEmployeeCreatedEvent(EmployeeCreatedEvent event);
}
