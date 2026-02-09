package com.example.teacherservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CalendarDateDto {

    @NotNull(message = "Date must not be null")
    private LocalDate date;





}
