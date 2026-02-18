package com.example.notificationservice.repository;

import com.example.notificationservice.entity.DailyGradeNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DailyGradeNotificationRepository extends JpaRepository<DailyGradeNotification, Long> {

    List<DailyGradeNotification> findByEventDateBetween(LocalDateTime start, LocalDateTime end);
}
