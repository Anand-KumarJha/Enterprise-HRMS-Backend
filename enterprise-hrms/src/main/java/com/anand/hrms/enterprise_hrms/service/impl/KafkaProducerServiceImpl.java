package com.anand.hrms.enterprise_hrms.service.impl;

import com.anand.hrms.enterprise_hrms.dto.EmployeeCreatedEvent;
import com.anand.hrms.enterprise_hrms.service.KafkaProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate;

    public KafkaProducerServiceImpl (KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendEmployeeCreatedEvent(EmployeeCreatedEvent event) {
        kafkaTemplate.send("employee-events", event.getId().toString(), event);
    }
}
