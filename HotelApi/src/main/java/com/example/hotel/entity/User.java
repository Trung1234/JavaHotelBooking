package com.example.hotel.entity;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


import lombok.Data;

@Data
public class User {
    private String id;
    private String name;

    private String username;

    private String email;

    private String password;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
    private Set<Role> roles;
}