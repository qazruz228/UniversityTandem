package com.example.teacherservice.service;

import com.example.teacherservice.enums.OutboxStatus;
import com.example.teacherservice.events.OutboxEvent;
import com.example.teacherservice.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OutboxEventRepository  outboxEventRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void saveEvent(Object event) {

        try {
            String payload = objectMapper.writeValueAsString(event);

            OutboxEvent outboxEvent = OutboxEvent.builder()
                    .payload(payload)
                    .outboxStatus(OutboxStatus.NEW)
                    .retryCount(3)
                    .createdAt(LocalDateTime.now())
                    .build();

            outboxEventRepository.save(outboxEvent);

        } catch (JsonParseException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }
}

