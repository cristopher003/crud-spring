package com.example.prueba.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(
        RuntimeException ex,
        HttpServletRequest request
    ) {
        ErrorResponse errorResponse = ErrorResponse.create(
            HttpStatus.NOT_FOUND.value(),
            "Error de Aplicación",
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
