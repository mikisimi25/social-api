package com.mapJump.api;

import com.mapJump.api.user.model.CreateUserDto;
import com.mapJump.api.user.model.User;
import com.mapJump.api.user.model.UserDto;
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

    public static UserDto convertUserToUserDto(User user) {

        return UserDto
                .builder()
                .username(user.getUsername())
                .build();

    }


}