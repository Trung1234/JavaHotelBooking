package com.example.hotel.common;

import com.example.hotel.exeption.DataAlreadyExistsException;
import com.example.hotel.exeption.InvalidInputException;
import com.example.hotel.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  Handle all internal server errors (500)
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = { Exception.class })  // Catch any Exception
    public ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request) {
        // Log the exception details (optional)
        ex.printStackTrace();

        // Create a custom error response object
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error: Something went wrong."+
                ex.getMessage()
        );

        // Return a ResponseEntity with status 500
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle the DataAlreadyExistsException
      * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExistsException(DataAlreadyExistsException ex, WebRequest request) {
        // Create a response body with error details
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());

        // Return a 409 Conflict status with the error message
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<?> handleInvalidInputException(InvalidInputException ex, WebRequest request) {
        // Create a response body with error details
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());

        // Return a 409 Conflict status with the error message
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }




    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        // Create a response body with error details
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());

        // Return a 400 bad request status with the error message
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

