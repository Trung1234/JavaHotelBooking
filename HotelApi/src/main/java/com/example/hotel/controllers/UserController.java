package com.example.hotel.controllers;

import com.example.hotel.common.JwtUtil;
import com.example.hotel.exeption.DataAlreadyExistsException;
import com.example.hotel.common.StringUtils;
import com.example.hotel.exeption.InvalidInputException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.hotel.dto.LoginDto;
import com.example.hotel.dto.SignUpDto;
import com.example.hotel.entity.User;
import com.example.hotel.services.UserService;

import java.util.Optional;

@Transactional
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        if (StringUtils.isNullorBlank(signUpDto.getEmail()) || StringUtils.isNullorBlank(signUpDto.getPassword())) {
            throw new IllegalArgumentException("Please input email and password ");
        }
        // Check if email already exists in the database
        if (userService.existsByEmail(signUpDto)) {
            throw new DataAlreadyExistsException("Email is already in use: " + signUpDto.getEmail());
        }

        // Validate input
        userService.validateUserInput(signUpDto);

        // Validate password strength
        if (!userService.isPasswordStrong(signUpDto.getPassword())) {
            throw new InvalidInputException("Password does not meet strength requirements.");
        }

        userService.createUser(signUpDto);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);

    }


//    @CrossOrigin(origins = "http://localhost:3000")  // Allow specific origin
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Optional<User> user = userService.authenticateUser(loginDto);
        if (user.isPresent()) {
            boolean isMatchPass = userService.matchPassword(loginDto.getPassword(),user.get().getPassword());
            if (!isMatchPass) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }else {
                String token = JwtUtil.generateToken(user.get().getId(),"ROLE_ADMIN");
                return ResponseEntity.ok(token);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
