package com.example.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.hotel.entity.User;

@Mapper
public interface UserMapper {

   void insertUser(User user);
   User selectUserByEmail(String email);
}