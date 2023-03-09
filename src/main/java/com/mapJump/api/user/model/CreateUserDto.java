package com.mapJump.api.user.model;

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