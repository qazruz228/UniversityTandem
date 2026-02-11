package com.example.teacherservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
//@Schema(description = "Стандартный ответ об ошибке")
public class ErrorResponse {

//    @Schema(example = "2025-05-10T12:00:00")
    private LocalDateTime timestamp;

//    @Schema(example = "400")
    private int status;

//    @Schema(example = "Bad Request")
    private String error;

//    @Schema(example = "Group name cannot be blank")
    private String message;

//    @Schema(example = "/api/groups")
    private String path;
}