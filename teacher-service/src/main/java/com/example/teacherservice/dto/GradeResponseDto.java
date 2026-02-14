package com.example.teacherservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GradeResponseDto {

    @Schema(description = "Оценка студента")
    private int grade;

}
