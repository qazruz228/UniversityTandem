package com.example.headteacherservice.exception;

public class TeacherAlreadyExistException extends RuntimeException {
    public TeacherAlreadyExistException(String message) {
        super(message);
    }
}
