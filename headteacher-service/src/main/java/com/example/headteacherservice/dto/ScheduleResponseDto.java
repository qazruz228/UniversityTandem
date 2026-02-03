package com.example.headteacherservice.dto;

import com.example.headteacherservice.entity.enumType.Weekday;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ScheduleResponseDto {

    @Schema(description = "Номер урока по порядку", example = "4")
    @Size(min = 1, max = 6)
    private int lessonOrder;

    @Schema(description = "день недели", example = "MONDAY")
    private Weekday dayOfWeek;

    @Schema(description = "Данные группы")
    @Valid
    private GroupDto groupDto;

    @Schema(description = "Данные группы")
    @Valid
    private SubjectDto subjectDto;

    @Schema(description = "Данные группы")
    @Valid
    private TeacherDto teacherDto;


}
