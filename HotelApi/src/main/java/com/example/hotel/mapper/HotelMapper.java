package com.example.hotel.mapper;

import com.example.hotel.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface HotelMapper {

    List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate,
                                   Integer guests);
}