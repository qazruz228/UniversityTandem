package com.example.notificationservice.listener;

import com.example.notificationservice.events.GradeEvent;
import com.example.notificationservice.handler.GradeEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GradeEventListener {

    private final GradeEventHandler gradeEventHandler;

    @KafkaListener(
            topics = "gradeTopic", groupId = "notification-service",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(GradeEvent event) {
        log.info("Received Kafka event: {} for student {}", event.getEventId(), event.getStudentEmail());
        gradeEventHandler.handle(event);
    }
}