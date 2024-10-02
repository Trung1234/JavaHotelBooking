package com.example.hotel.services;

import com.example.hotel.exeption.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.common.PasswordEncryption;
import com.example.hotel.dto.LoginDto;
import com.example.hotel.dto.SignUpDto;
import com.example.hotel.entity.User;
import com.example.hotel.mapper.UserMapper;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

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
        user.setInsertDate(signUpDto.getInsertDate());
        user.setUpdateDate(signUpDto.getUpdateDate());
        userMapper.insertUser(user);
    }


    public boolean existsByEmail(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        return userMapper.selectUserByEmail(email).isPresent();
    }

    public Optional<User> authenticateUser(LoginDto loginDto) {
    	String email = loginDto.getEmail();
        return userMapper.selectUserByEmail(email);
    }

    public boolean matchPassword(String plainPassword,String hahedPasword ) {
    	return PasswordEncryption.checkpw(plainPassword, hahedPasword);
    }

    // Validate user input
    public void validateUserInput(SignUpDto user) throws InvalidInputException {
//        if (user.getEmail() == null || !isValidEmail(user.getEmail())) {
//            throw new InvalidInputException("Invalid email format.");
//        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new InvalidInputException("Password must be at least 8 characters.");
        }
    }

    // Check if the password meets the security standards
    public boolean isPasswordStrong(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[0-9]").matcher(password).find() &&
                Pattern.compile("[A-Z]").matcher(password).find();
    }

    // Email validation (simple regex)
//    private boolean isValidEmail(String email) {
//        return Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).find();
//    }

}