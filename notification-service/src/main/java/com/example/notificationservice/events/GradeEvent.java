package com.example.notificationservice.events;

import com.example.notificationservice.enums.EventType;
import com.example.notificationservice.enums.converter.EventTypeConverter;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class GradeEvent {

    private UUID eventId;
    private Long studentId;
    private String studentEmail;
    private String subject;
    private LocalDate calendarDate;
    private int gradeValue;

    @Convert(converter = EventTypeConverter.class)
    private EventType eventType;

}
