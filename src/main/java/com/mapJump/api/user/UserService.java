package com.mapJump.api.user;

import com.mapJump.api.user.model.CreateUserDto;
import com.mapJump.api.user.model.LoginUserDto;
import com.mapJump.api.user.model.User;
import com.mapJump.api.user.model.UserDto;

import java.util.List;

public interface UserService {

    UserDto loginUser(LoginUserDto loginUserDto);

    public User getUserByUsername(String username);

    public List<User> getAllUsers();

    public User createUser(CreateUserDto user);

}
