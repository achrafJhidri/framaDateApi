package com.example.framadate.mapper;

import com.example.framadate.entity.User;
import com.example.framadate.model.userDtos.PostUserDto;
import com.example.framadate.model.userDtos.PutUserDto;
import com.example.framadate.model.userDtos.UserDto;
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

    public void toEntity(User user, PutUserDto userDto) {
        if ( !isNullOrEmpty(userDto.getName()))
            user.setName(userDto.getName() );

        if ( !isNullOrEmpty(userDto.getEmail()))
            user.setEmail(userDto.getEmail());
    }

    public User toEntity(PostUserDto userDto) {
        if ( isNullOrEmpty(userDto.getName() ) || isNullOrEmpty(userDto.getEmail()) )
            throw new IllegalArgumentException("empty or null parametters");
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }
    public static boolean isNullOrEmpty(String s){
        return s == null ||s.trim().isEmpty();
    }

}
