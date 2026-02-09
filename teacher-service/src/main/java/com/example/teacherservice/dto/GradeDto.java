package com.example.teacherservice.dto;

import com.example.teacherservice.enums.Subject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GradeDto {

    @Min(value = 2, message = "Grade must be >= 2")
    @Max(value = 5, message = "Grade must be <= 5")
    private int grade;

    @NotNull(message = "Student id must not be null")
    private Long studentId;

    @NotNull(message = "Subject must not be null")
    private Subject subject;

    @NotNull(message = "Date id must not be null")
    private Long dateId;

}
