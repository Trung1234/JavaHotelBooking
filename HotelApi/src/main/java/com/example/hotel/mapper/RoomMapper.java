package com.example.hotel.mapper;

import com.example.hotel.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;


@Mapper
public interface RoomMapper {

    List<Room> findRooms(Integer offSet, Integer pageSize,
                         String roomType, Integer roomNumber, Double rate);
}