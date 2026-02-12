package com.example.teacherservice.scheduler;

import com.example.teacherservice.enums.OutboxStatus;
import com.example.teacherservice.events.OutboxEvent;
import com.example.teacherservice.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxScheduler {

    private final OutboxEventRepository outboxEventRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final int BATCH_SIZE = 50;
    private static final int MAX_RETRY = 5;

    @Scheduled(fixedDelayString = "${outbox.polling.interval:5000}")
    @Transactional
    public void processOutbox() {

        List<OutboxEvent> events = outboxEventRepository.findBatch(PageRequest.of(0, BATCH_SIZE));

        for (OutboxEvent event : events) {
            processSingleEvent(event);
        }
    }

    private void processSingleEvent(OutboxEvent event) {

        try {
            kafkaTemplate.send("gradeTopic", event.getPayload()).get();

            event.setOutboxStatus(OutboxStatus.SENT);
            event.setProcessedAt(LocalDateTime.now());

        } catch (Exception ex) {

            log.error("Failed to send event id={}", event.getId(), ex);

            int retry = event.getRetryCount() + 1;
            event.setRetryCount(retry);

            if (retry >= MAX_RETRY) {
                event.setOutboxStatus(OutboxStatus.FAILED);
            }
        }
    }
}

