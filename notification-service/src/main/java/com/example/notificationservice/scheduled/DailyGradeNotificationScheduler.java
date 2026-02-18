package com.example.notificationservice.scheduled;

import com.example.notificationservice.handler.GradeEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DailyGradeNotificationScheduler {

    private final GradeEventHandler gradeEventHandler;

    @Scheduled(cron = "0 0 14 * * *")
    public void sendDailyNotifications() {
        log.info("Starting daily grade notifications at {}", LocalDateTime.now());
        gradeEventHandler.sendDailyNotifications();
    }
}
