package com.example.teacherservice.events;

import com.example.teacherservice.enums.EventType;
import com.example.teacherservice.enums.converter.EventTypeConverter;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GradeEvent {

    private Long studentId;
    private String studentEmail;
    private String subject;
    private LocalDate calendarDate;
    private int gradeValue;

    @Convert(converter = EventTypeConverter.class)
    private EventType eventType;

}
