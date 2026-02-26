package com.example.headteacherservice.dto;

import java.time.LocalDateTime;
//import net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder
public record ApiResponse (
        LocalDateTime timestamp,
        String message,
        Object body){
}
