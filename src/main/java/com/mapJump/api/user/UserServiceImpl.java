package com.mapJump.api.user;

import com.mapJump.api.Converter;
import com.mapJump.api.user.model.CreateUserDto;
import com.mapJump.api.user.model.LoginUserDto;
import com.mapJump.api.user.model.User;
import com.mapJump.api.user.model.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.mapJump.api.Converter.convertUserToUserDto;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDto loginUser(LoginUserDto loginUserDto) {
        String username = loginUserDto.getUsername();

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            if(loginUserDto.getPassword().equals(user.getPassword())) {
                return convertUserToUserDto(user);
            }
            return convertUserToUserDto(user);

        }

        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(CreateUserDto userDto) {

        if(userDto.getUsername().isEmpty() || userDto.getPassword().isEmpty()) {
            return null;
        }

        if(userRepository.findByUsername(userDto.getUsername()) != null) {
            return null;
        }

        User user = Converter.convertUserDtoToUser(userDto);

        return userRepository.save(user);

    }
}
