package com.example.hotel.services;

import com.example.hotel.common.Constants;
import com.example.hotel.dto.SearchRoomDto;
import com.example.hotel.entity.Room;
import com.example.hotel.mapper.HotelMapper;
import com.example.hotel.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class RoomService {
    @Autowired
    private RoomMapper roomMapper;

    /**
     *  Method to find available rooms asynchronously
     * @return
     */
    public List<Room> findRooms(Integer page, SearchRoomDto searchRoomDto) {
        Integer offSet = (page - 1 ) *  Constants.PAGE_SIZE;
        return roomMapper.findRooms(offSet, Constants.PAGE_SIZE
                ,searchRoomDto.getRoomType(), searchRoomDto.getRoomNumber(), searchRoomDto.getRate());
    }
}