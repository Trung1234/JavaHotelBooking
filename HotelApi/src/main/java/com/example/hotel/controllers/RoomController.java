package com.example.hotel.controllers;

import com.example.hotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("api")
public class RoomController {
    @GetMapping("/test")
    public String test() {
        return "Hello ";
    }
}

