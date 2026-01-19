package com.dibyatech.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
public class GlobalExceptionHandler {

//    // Handles @Valid validation failures
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationErrors(
//            MethodArgumentNotValidException ex) {
//
//        Map<String, String> errors = new HashMap<>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.put(error.getField(), error.getDefaultMessage());
//        }
//
//        return ResponseEntity.badRequest().body(errors);
//    }
//
//    // Optional: Handles jakarta ConstraintViolations
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Map<String, String>> handleConstraintViolation(
//            ConstraintViolationException ex) {
//
//        Map<String, String> errors = new HashMap<>();
//        ex.getConstraintViolations().forEach(cv ->
//                errors.put(cv.getPropertyPath().toString(), cv.getMessage())
//        );
//
//        return ResponseEntity.badRequest().body(errors);
//    }
//
//    // Fallback for all other exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(Map.of("error", ex.getMessage()));
//    }
}
