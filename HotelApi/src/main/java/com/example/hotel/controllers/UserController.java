package com.example.hotel.controllers;

import com.example.hotel.common.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.dto.LoginDto;
import com.example.hotel.dto.SignUpDto;
import com.example.hotel.entity.User;
import com.example.hotel.services.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        userService.createUser(signUpDto);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        User user = userService.authenticateUser(loginDto);
        if (user != null) {
            boolean isMatchPass = userService.matchPassword(loginDto.getPassword(),user.getPassword());
            if (!isMatchPass) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }else {
                String token = JwtUtil.generateToken(user.getUsername());
                return ResponseEntity.ok(token);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
