package com.example.hotel.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
}
