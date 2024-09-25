package com.example.hotel.dto;

import lombok.Data;


@Data
public class HotelDto extends  BaseDto {
    private String checkInDate;
    private String checkOutDate;
    private Integer guests;
}