package com.example.hotel.entity;


import java.util.Set;


import lombok.Data;

@Data
public class User {



    private long id;
    private String name;

    private String username;

    private String email;

    private String password;

    private Set<Role> roles;
}