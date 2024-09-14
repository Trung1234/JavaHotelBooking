package com.example.hotel.services;

import com.example.hotel.entity.User;
import com.example.hotel.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;



    public void createUser(User user) {
        userMapper.insertUser(user);
    }

}