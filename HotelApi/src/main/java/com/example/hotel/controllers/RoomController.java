package com.example.hotel.controllers;

import com.example.hotel.common.JwtUtil;
import com.example.hotel.dto.HotelDto;
import com.example.hotel.dto.LoginDto;
import com.example.hotel.dto.SearchRoomDto;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import com.example.hotel.services.HotelService;
import com.example.hotel.services.RoomService;
import com.example.hotel.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Transactional
@RestController
@RequestMapping("api")
public class RoomController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/test")
    public String test() {
        return "Hello ";
    }

    @PostMapping("/findRooms")
    public ResponseEntity<List<Room>> findRooms(@RequestBody @Valid SearchRoomDto searchRoomDto, @RequestParam Integer page) throws ExecutionException, InterruptedException {
        List<Room> roomList = roomService.findRooms(page,searchRoomDto);
        return ResponseEntity.ok(roomList);
    }

    @PostMapping("/findAvailableRooms")
    public ResponseEntity<List<Room>> findAvailableRooms( @RequestBody @Valid HotelDto hotelDto) throws ExecutionException, InterruptedException {

        CompletableFuture<List<Room>> roomList = hotelService.findAvailableRooms(hotelDto.getCheckInDate(),
                hotelDto.getCheckOutDate(),hotelDto.getGuests());

        return ResponseEntity.ok(roomList.get());
    }
}

