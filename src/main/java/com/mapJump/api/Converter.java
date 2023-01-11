package com.example.demo;

import com.example.demo.user.CreateUserDto;
import com.example.demo.user.User;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public static User convertUserDtoToUser(CreateUserDto userDto) {

        return User
                .builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

    }

}