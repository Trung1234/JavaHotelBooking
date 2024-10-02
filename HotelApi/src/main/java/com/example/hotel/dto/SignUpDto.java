package com.example.hotel.dto;

import com.example.hotel.validator.EmailConstraint;
import lombok.Data;

@Data
public class SignUpDto extends  BaseDto{
    private String name;
    private String username;
    @EmailConstraint
    private String email;
    private String password;
}