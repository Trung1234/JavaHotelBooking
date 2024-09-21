package com.example.hotel.dto;

import lombok.Data;

@Data
public class SignUpDto extends  BaseDto{
    private String name;
    private String username;
    private String email;
    private String password;
}