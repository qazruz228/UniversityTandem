package com.example.headteacherservice.dto;

import com.example.headteacherservice.entity.enumType.Weekday;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.security.sasl.SaslClient;

@Data
public class ScheduleCreateOrUpdateDto {

    @Schema(description = "день недели", example = "MONDAY")
    @NotNull(message = "dayOfWeek is required")
    private Weekday dayOfWeek;

    @Schema(description = "Номер урока по порядку", example = "4")
    @Size(min = 1, max = 6)
    private int lessonOrder;

    @Schema(description = "id группы")
    @NotNull(message = "groupId is required")
    private Long groupId;

    @Schema(description = "id предмета")
    @NotNull(message = "subjectId is required")
    private Long subjectId;

    @Schema(description = "id учителя")
    @NotNull(message = "teacherId is required")
    private Long teacherId;
}
