package com.example.framadate.service;

import com.example.framadate.entity.User;
import com.example.framadate.mapper.UserMapper;
import com.example.framadate.model.userDtos.PostUserDto;
import com.example.framadate.model.userDtos.PutUserDto;
import com.example.framadate.model.userDtos.UserDto;
import com.example.framadate.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private PostUserDto userDto ;
    private User user;
    private UserDto userDtoSaved;

    @BeforeEach
    void init(){
        userDto = new PostUserDto("test","email@fr.dom");

        user = new User();
        user.setId(1L);
        user.setName("achraf");
        user.setEmail("email@fr.dom");

        userDtoSaved = new UserDto(1L,"test","email@fr.dom");
    }
    @Test
    void givenValidUser_whenSave_thenValidName() {


        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.saveAndFlush(any(User.class))).then(returnsFirstArg());
        when(userMapper.toDto(any(User.class))).thenReturn(userDtoSaved);

        UserDto savedUser= userService.create(userDto);

        verify(userMapper,times(1)).toEntity(userDto);
        verify(userRepository,times(1)).saveAndFlush(user);
        verify(userMapper,times(1)).toDto(user);

        assertThat(savedUser.getName()).isNotBlank();

    }
    @Test
    void givenValidUser_whenSave_thenValidEmail() {

        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.saveAndFlush(any(User.class))).then(returnsFirstArg());
        when(userMapper.toDto(any(User.class))).thenReturn(userDtoSaved);

        UserDto savedUser= userService.create(userDto);

        verify(userMapper,times(1)).toEntity(userDto);
        verify(userRepository,times(1)).saveAndFlush(user);
        verify(userMapper,times(1)).toDto(user);

        assertThat(savedUser.getEmail()).isNotBlank();
    }
    @Test
    void givenNullEmail_whenUpdate_thenValidEmail() {
        PutUserDto userDto = new PutUserDto("achraf",null);
        var opt = Optional.of(user);
        userDtoSaved = new UserDto(1L,"test","test@ud.fr");

        when(userRepository.findById(1L)).thenReturn(opt);
        when(userRepository.saveAndFlush(opt.get())).then(returnsFirstArg());
        when(userMapper.toDto(user)).thenReturn(userDtoSaved);

        UserDto savedUser= userService.update(1L,userDto);

        verify(userRepository,times(1)).findById(1L);
        verify(userMapper,times(1)).toEntity(user, userDto);
        verify(userRepository,times(1)).saveAndFlush(user);
        verify(userMapper,times(1)).toDto(user);


        assertThat(savedUser.getEmail()).isNotNull();
    }


}