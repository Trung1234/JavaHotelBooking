package com.example.hotel.services;

import com.example.hotel.entity.Room;
import com.example.hotel.mapper.HotelMapper;
import com.example.hotel.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class HotelService {
    @Autowired
    private HotelMapper hotelMapper;

    /**
     *  Method to find available rooms asynchronously
     * @param checkInDate
     * @param checkOutDate
     * @param guests
     * @return
     */
    @Async
    public CompletableFuture<List<Room>> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate,
                                                            Integer guests) {
        List<Room> roomList = hotelMapper.findAvailableRooms( checkInDate,  checkOutDate,
                 guests);
        return CompletableFuture.supplyAsync(() -> {
            // Filter the rooms to find available ones
            return   roomList;
        });
    }
}
