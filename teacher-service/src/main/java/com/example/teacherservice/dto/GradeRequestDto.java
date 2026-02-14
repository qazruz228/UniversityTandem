package com.example.teacherservice.dto;

import com.example.teacherservice.enums.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GradeRequestDto {

    @Schema(description = "Оценка студента")
    @Min(value = 2, message = "Grade must be >= 2")
    @Max(value = 5, message = "Grade must be <= 5")
    private int grade;

    @Schema(description = "Id студента")
    @NotNull(message = "Student id must not be null")
    private Long studentId;

    @Schema(description = "Учебный предмет")
    @NotNull(message = "Subject must not be null")
    private Subject subject;

    @Schema(description = "Id даты")
    @NotNull(message = "Date id must not be null")
    private Long dateId;

    @Schema(description = "Id группы")
    @NotNull(message = "group id must not be null")
    private Long groupId;

}
