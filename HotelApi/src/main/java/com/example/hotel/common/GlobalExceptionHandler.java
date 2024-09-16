package com.example.hotel.common;

import com.example.hotel.exeption.DataAlreadyExistsException;
import com.example.hotel.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle the DataAlreadyExistsException
      * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExistsException(DataAlreadyExistsException ex, WebRequest request) {
        // Create a response body with error details
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(ex.getMessage());
        // Return a 409 Conflict status with the error message
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        // Create a response body with error details
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        // Return a 400 bad request status with the error message
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

