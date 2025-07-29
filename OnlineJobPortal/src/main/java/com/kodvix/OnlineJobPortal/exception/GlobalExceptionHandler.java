package com.kodvix.OnlineJobPortal.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle other exceptions (optional)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        Map<String, String> response = new HashMap<>();

        if (message.contains("Duplicate entry") && message.contains("UK6dotkott2kjsp8vw4d0m25fb7")) {
            // Assuming this constraint is for email
            response.put("error", "Email already exists. Please use a different email.");
        } else if (message.contains("Duplicate entry") && message.contains("another_constraint_for_phone")) {
            // Use your actual constraint name for phone number
            response.put("error", "Phone number already exists. Please use a different number.");
        } else if (message.contains("Duplicate entry") && message.contains("unique_constraint_for_first_last")) {
            response.put("error", "Username already exists. Please choose another.");
        } else {
            response.put("error", "Data integrity violation: " + message);
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
