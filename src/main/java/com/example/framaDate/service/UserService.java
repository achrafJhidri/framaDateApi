package com.example.framadate.service;

import com.example.framadate.entity.User;
import com.example.framadate.mapper.UserMapper;
import com.example.framadate.model.userDtos.PostUserDto;
import com.example.framadate.model.userDtos.PutUserDto;
import com.example.framadate.model.userDtos.UserDto;
import com.example.framadate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDto).orElse(null);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto create(PostUserDto userDto) {
        User userEntity = userMapper.toEntity(userDto);
        userEntity = userRepository.saveAndFlush(userEntity);
        return userMapper.toDto(userEntity);
    }

    public UserDto update(Long userId, PutUserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("user " + userId + " not found");
        }

        User user = userOptional.get();

        userMapper.toEntity(user, userDto);
        user = userRepository.saveAndFlush(user);
        return userMapper.toDto(user);

    }

    public String deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("user " + userId + " not found");
        }
        userRepository.delete(user.get());
        return user.get().toString();
    }
}
