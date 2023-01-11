package com.mapJump.api.user;

import com.mapJump.api.Converter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> contactColl = userRepository.findAll();

        if(contactColl.isEmpty()) {
            return null;
        }

        return contactColl;
    }

    @Override
    public User createUser(CreateUserDto userDto) {

        if(userDto.getUsername().isEmpty() || userDto.getPassword().isEmpty()) {
            return null;
        }

        User user = Converter.convertUserDtoToUser(userDto);

        return userRepository.save(user);

    }
}
