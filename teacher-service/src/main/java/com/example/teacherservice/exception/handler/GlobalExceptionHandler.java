package com.example.teacherservice.exception.handler;

import com.example.teacherservice.exception.BusinessValidationException;
import com.example.teacherservice.exception.DayIsNotWorkingException;
import com.example.teacherservice.exception.ErrorResponse;
import com.example.teacherservice.exception.GradeAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GradeAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleGradeAlreadyExists(
            GradeAlreadyExistsException ex,
            HttpServletRequest request
    ) {

        log.warn("Grade already exists: {}", ex.getMessage());

        return buildResponse(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                request.getRequestURI()
        );
    }


    @ExceptionHandler(DayIsNotWorkingException.class)
    public ResponseEntity<ErrorResponse> handleDayIsNotWorking(
            DayIsNotWorkingException ex,
            HttpServletRequest request
    ) {

        log.warn("Attempt to put grade on non-working day: {}", ex.getMessage());

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI()
        );
    }


    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ErrorResponse> handleBusinessValidation(
            BusinessValidationException ex,
            HttpServletRequest request
    ) {

        log.warn("Business validation failed: {}", ex.getMessage());

        return buildResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                ex.getMessage(),
                request.getRequestURI()
        );
    }


    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String message,
            String path
    ) {

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();

        return new ResponseEntity<>(response, status);
    }


}
