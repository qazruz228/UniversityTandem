package com.example.headteacherservice.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Schema(description = "Стандартный ответ об ошибке")
public class ErrorResponse {

    @Schema(example = "2025-05-10T12:00:00")
    private LocalDateTime timestamp;

    @Schema(example = "400")
    private int status;

    @Schema(example = "Bad Request")
    private String error;

    @Schema(example = "Group name cannot be blank")
    private String message;

    @Schema(example = "/api/groups")
    private String path;
}