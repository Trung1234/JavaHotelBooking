package com.example.hotel.entity;


import java.time.LocalDateTime;
import java.util.Set;


import lombok.Data;

@Data
public class User {
    private long id;
    private String name;

    private String username;

    private String email;

    private String password;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
    private Set<Role> roles;
}