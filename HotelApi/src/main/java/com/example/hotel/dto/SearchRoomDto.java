package com.example.hotel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchRoomDto {
    private String roomType;
    @NotNull(message = "room number is required")
    private Integer roomNumber;
    private Double rate;
}
