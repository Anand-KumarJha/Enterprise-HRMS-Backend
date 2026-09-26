package com.anand.hrms.enterprise_hrms.service.impl;

import com.anand.hrms.enterprise_hrms.dto.EmployeeCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeKafkaConsumer {
    @KafkaListener(
            topics = "employee-events",
            groupId = "hrms-group"
    )
    public void consumeEmployeeCreatedEvent(EmployeeCreatedEvent event){
        System.out.println("Employee Created Event Received: " + event.getId() + " - " + event.getEmail());
    }
}
