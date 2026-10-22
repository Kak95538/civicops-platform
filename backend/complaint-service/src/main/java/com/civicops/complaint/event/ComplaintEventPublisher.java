
package com.civicops.complaint.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ComplaintEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ComplaintEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishComplaintCreated(String eventMessage) {
        kafkaTemplate.send("complaint-created", eventMessage);
    }
}
