package com.example.hotel.mapper;

import com.example.hotel.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

   void insertUser(User user);

}