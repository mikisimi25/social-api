package com.mapJump.api.user;

import com.mapJump.api.user.model.CreateUserDto;
import com.mapJump.api.user.model.LoginUserDto;
import com.mapJump.api.user.model.User;
import com.mapJump.api.user.model.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private UserServiceImpl userService;

    //Login with credentials
    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginUserDto loginUserDto) throws Exception {
        UserDto user = userService.loginUser(loginUserDto);

        if(user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    //Get user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws Exception {
        Optional<User> user = Optional.ofNullable(userService.getUserByUsername(username));

        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    //Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        Optional<List<User>> userColl = Optional.ofNullable(userService.getAllUsers());

        if(userColl.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userColl.get());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //Save user
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody CreateUserDto userDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

}
