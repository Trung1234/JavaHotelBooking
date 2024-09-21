package com.example.hotel.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDto {
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
}
