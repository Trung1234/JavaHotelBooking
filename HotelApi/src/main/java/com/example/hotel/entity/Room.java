package com.example.hotel.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Room {
    public enum RoomType {
        SINGLE, DOUBLE, SUITE
    }

    private int id; // Primary Key
    private String roomNumber;
    private RoomType roomType;
    private int capacity;
    private double rate;
    private String description;
}
