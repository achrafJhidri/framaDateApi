package com.example.framadate.service;

import com.example.framadate.entity.Comment;
import com.example.framadate.entity.User;
import com.example.framadate.mapper.UserMapper;
import com.example.framadate.model.UserDto;
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

    public UserDto findUserDtoById(Long id){
       User user =   findUserById(id) ;
       return  user != null ? userMapper.toDto(user) : null;
    }
    public User findUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<UserDto> findAllDtos() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto save(UserDto userDto) {
        User userEntity = new User();
        userMapper.toEntity(userEntity,userDto);
        userEntity = userRepository.saveAndFlush(userEntity);
        userDto.setId(userEntity.getId());
        return userDto;

    }

    public UserDto updateOne(Long id, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) { //Not Found in db
            return null ;
        }

        User user = userOptional.get();

        userMapper.toEntity(user,userDto);
        userRepository.saveAndFlush(user);
        return userMapper.toDto(user);

    }

    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) { //Not Found in db
            return null ;
        }
        userRepository.delete(user.get());
        return user.get().toString();
    }
}
