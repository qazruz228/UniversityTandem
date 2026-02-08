package com.example.headteacherservice.dto;

import java.time.LocalDateTime;

public record ApiResponse (
        LocalDateTime timestamp,
        String message,
        Object body){
}
