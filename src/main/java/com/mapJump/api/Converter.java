package com.mapJump.api;

import com.mapJump.api.user.CreateUserDto;
import com.mapJump.api.user.User;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public static User convertUserDtoToUser(CreateUserDto userDto) {

        return User
                .builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();

    }

}