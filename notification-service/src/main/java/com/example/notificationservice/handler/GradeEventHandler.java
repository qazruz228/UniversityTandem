package com.example.notificationservice.handler;

import com.example.notificationservice.entity.DailyGradeNotification;
import com.example.notificationservice.entity.ProcessedEvent;
import com.example.notificationservice.enums.EventType;
import com.example.notificationservice.events.GradeEvent;
import com.example.notificationservice.repository.DailyGradeNotificationRepository;
import com.example.notificationservice.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GradeEventHandler {

    private final ProcessedEventRepository processedEventRepository;
    private final DailyGradeNotificationRepository dailyGradeNotificationRepository;
    private final JavaMailSender mailSender;

    @Transactional
    public void handle(GradeEvent event) {

        if (processedEventRepository.existsById(event.getEventId())) {
            log.info("Event {} already processed, skipping", event.getEventId());
            return;
        }

        processedEventRepository.save(new ProcessedEvent(event.getEventId().toString(), LocalDateTime.now()));
        log.info("Saved processed event {}", event.getEventId());

        DailyGradeNotification notification = new DailyGradeNotification(
                null,
                event.getStudentEmail(),
                event.getSubject(),
                event.getGradeValue(),
                event.getEventType(),
                LocalDateTime.now()
        );
        dailyGradeNotificationRepository.save(notification);
        log.info("Saved daily notification for student");
    }

    public void sendDailyNotifications() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(23, 59, 59);

        List<DailyGradeNotification> notifications =
                dailyGradeNotificationRepository.findByEventDateBetween(startOfDay, endOfDay);

        log.info("Found {} notifications for today", notifications.size());

        Map<String, List<DailyGradeNotification>> groupedByStudent = notifications.stream()
                .collect(Collectors.groupingBy(DailyGradeNotification::getStudentEmail));

        for (String email : groupedByStudent.keySet()) {
            List<DailyGradeNotification> studentNotifications = groupedByStudent.get(email);
            StringBuilder text = new StringBuilder("Сегодня вы получили следующие оценки:\n");

            for (DailyGradeNotification notification : studentNotifications) {
                text.append(notification.getSubject())
                        .append(" : ")
                        .append(notification.getGradeValue())
                        .append(notification.getEventType() == EventType.UPDATED ? " (исправлено)\n" : "\n");
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Итоги учебного дня");
            message.setText(text.toString());

            try {
                mailSender.send(message);
                log.info("Sent daily email to {}", email);
            } catch (Exception e) {
                log.error("Failed to send email to {}: {}", email, e.getMessage(), e);
            }
        }
        dailyGradeNotificationRepository.deleteAll(notifications);
        log.info("Cleared {} daily notifications after sending emails", notifications.size());
    }
}
