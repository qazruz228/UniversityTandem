package com.example.teacherservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CalendarDateDto {

    @Schema(description = "Дата")
    @NotNull(message = "Date must not be null")
    private LocalDate date;

    @Schema(description = "Рабочий или выходной")
    private boolean isWorking;





}
