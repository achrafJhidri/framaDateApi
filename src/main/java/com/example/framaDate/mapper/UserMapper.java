package com.example.framadate.mapper;

import com.example.framadate.entity.Comment;
import com.example.framadate.entity.User;
import com.example.framadate.entity.Vote;
import com.example.framadate.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDto(User user) {

       return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public void toEntity(User user,UserDto userDto) {
        if ( userDto.getName() != null)
            user.setName(userDto.getName() );

        if ( userDto.getEmail() != null)
            user.setEmail(userDto.getEmail());
    }
}
