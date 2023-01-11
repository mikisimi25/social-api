package com.example.demo.user;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User createUser(CreateUserDto user);

}
