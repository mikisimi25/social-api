package com.mapJump.api.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CreateUserDto {

    private String username;
    private String password;

}