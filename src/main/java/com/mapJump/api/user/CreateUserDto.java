package com.example.demo.user;

import lombok.*;

@AllArgsConstructor
@Data
public class CreateUserDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

}