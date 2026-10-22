
package com.civicops.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ComplaintEventConsumer {

    @KafkaListener(
    	topics = "complaint-created",
        groupId = "civicops-notification-group"
    )
    public void consume(String message) {
        System.out.println("Notification Service received message: " + message);
    }
}
