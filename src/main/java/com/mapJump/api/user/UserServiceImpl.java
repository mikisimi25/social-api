package com.example.demo.user;

import java.util.List;

import com.example.demo.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> contactColl = userRepository.findAll();

        if(!contactColl.isEmpty()) {
            return contactColl;
        } else {
            throw new EntityNotFoundException();
        }

    }

    @Override
    public User createUser(CreateUserDto userDto) {

        User user = Converter.convertUserDtoToUser(userDto);

        return userRepository.save(user);
    }
}
