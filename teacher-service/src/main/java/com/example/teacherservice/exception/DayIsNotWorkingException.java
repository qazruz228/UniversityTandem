package com.example.teacherservice.exception;

public class DayIsNotWorkingException extends RuntimeException {
    public DayIsNotWorkingException(String message) {
        super(message);
    }
}
