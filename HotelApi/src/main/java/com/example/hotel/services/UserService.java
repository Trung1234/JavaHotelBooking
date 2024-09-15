package com.example.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.common.PasswordEncryption;
import com.example.hotel.dto.LoginDto;
import com.example.hotel.dto.SignUpDto;
import com.example.hotel.entity.User;
import com.example.hotel.mapper.UserMapper;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;



    public void createUser(SignUpDto signUpDto) {
        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(PasswordEncryption.hashPassword(signUpDto.getPassword()));
        userMapper.insertUser(user);
    }

    public boolean existsByEmail(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        return Objects.nonNull(userMapper.selectUserByEmail(email));
    }

    public User authenticateUser(LoginDto loginDto) {
    	String email = loginDto.getEmail();
        return userMapper.selectUserByEmail(email);
    }
    public boolean matchPassword(String plainPassword,String hahedPasword ) {
    	return PasswordEncryption.checkpw(plainPassword, hahedPasword);
    }

}