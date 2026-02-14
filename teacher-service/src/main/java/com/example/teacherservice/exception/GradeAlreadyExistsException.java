package com.example.teacherservice.exception;

public class GradeAlreadyExistsException extends RuntimeException {
    public GradeAlreadyExistsException(String message) {
        super(message);
    }
}
