package com.example.framadate.service;

import com.example.framadate.entity.User;
import com.example.framadate.mapper.UserMapper;
import com.example.framadate.model.userDtos.PostUserDto;
import com.example.framadate.model.userDtos.PutUserDto;
import com.example.framadate.model.userDtos.UserDto;
import com.example.framadate.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

    @Mock
    private PostUserDto userDto ;
    @Mock
    private User user;
    @Mock
    private UserDto userDtoSaved;


    @Test
    void givenValidUser_whenCreate_thenValidUserSaved() {
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.saveAndFlush(any(User.class))).then(returnsFirstArg());
        when(userMapper.toDto(any(User.class))).thenReturn(userDtoSaved);

        userService.create(userDto);

        verify(userMapper, times(1)).toEntity(userDto);
        verify(userRepository, times(1)).saveAndFlush(user);
        verify(userMapper, times(1)).toDto(user);


    }

    @Test
    void givenInvalidUser_whenUpdate_thenValidUserSaved() {
        PutUserDto userDto = new PutUserDto("achraf", null);
        var opt = Optional.of(user);
        userDtoSaved = new UserDto(1L, "test", "test@ud.fr");

        when(userRepository.findById(1L)).thenReturn(opt);
        when(userRepository.saveAndFlush(opt.get())).then(returnsFirstArg());
        when(userMapper.toDto(user)).thenReturn(userDtoSaved);

        userService.update(1L, userDto);

        verify(userRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).toEntity(user, userDto);
        verify(userRepository, times(1)).saveAndFlush(user);
        verify(userMapper, times(1)).toDto(user);

    }


}