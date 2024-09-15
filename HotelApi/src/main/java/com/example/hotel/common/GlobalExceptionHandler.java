package com.example.hotel.common;

import com.example.hotel.exeption.EmailAlreadyExistsException;
import com.example.hotel.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle the EmailAlreadyExistsException
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest request) {
        // Create a response body with error details
        //ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(ex.getMessage());
        // Return a 409 Conflict status with the error message
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Other exception handlers can go here
}
