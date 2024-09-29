package com.example.hotel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


@Data
public class HotelDto extends  BaseDto {
    @NotEmpty(message = "Check-in date is required")
    @NotNull(message = "Check-in date is required")
    @JsonFormat(pattern = "MM/dd/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String checkInDate;
    @NotEmpty(message = "Check-out date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    @NotNull(message = "Check-out date is required")
    private String checkOutDate;

    @NotNull(message = "Number of guests is required")
    @Min(value = 1, message = "At least 1 guest is required")
    private Integer guests;
}