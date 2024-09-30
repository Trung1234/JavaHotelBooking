package com.example.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.hotel.entity.User;

import java.util.Optional;

@Mapper
public interface UserMapper {

   void insertUser(User user);
   Optional<User> selectUserByEmail(String email);
}