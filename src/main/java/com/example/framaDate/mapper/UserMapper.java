package com.example.framadate.mapper;

import com.example.framadate.entity.User;
import com.example.framadate.model.UserDto;
import org.springframework.stereotype.Component;

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
