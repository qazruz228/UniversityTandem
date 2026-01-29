package com.example.headteacherservice.dto;

import com.example.headteacherservice.entity.enumType.Weekday;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ScheduleCreateOrUpdateDto {

    @NotNull(message = "dayOfWeek is required")
    private Weekday dayOfWeek;

    @Size(min = 1, max = 6)
    private int lessonOrder;

    @NotNull(message = "groupId is required")
    private Long groupId;

    @NotNull(message = "subjectId is required")
    private Long subjectId;

    @NotNull(message = "teacherId is required")
    private Long teacherId;
}
