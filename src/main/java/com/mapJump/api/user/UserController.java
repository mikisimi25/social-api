package com.mapJump.api.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private UserServiceImpl userService;

    //Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    //Save user
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody CreateUserDto userDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

}
