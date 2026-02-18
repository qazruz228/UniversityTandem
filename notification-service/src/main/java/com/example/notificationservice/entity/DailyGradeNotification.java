package com.example.notificationservice.entity;

import com.example.notificationservice.enums.EventType;
import com.example.notificationservice.enums.converter.EventTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_grade_notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyGradeNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail;

    private String subject;

    private int gradeValue;

    @Convert(converter = EventTypeConverter.class)
    private EventType eventType;

    private LocalDateTime eventDate;
}