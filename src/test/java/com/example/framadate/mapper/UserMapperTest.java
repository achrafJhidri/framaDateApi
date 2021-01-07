package com.example.framadate.mapper;

import com.example.framadate.entity.User;
import com.example.framadate.model.user_dtos.PostUserDto;
import com.example.framadate.model.user_dtos.PutUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();
    private PostUserDto postUserDto;
    private PutUserDto putUserDto;
    private User userEntity;


    @BeforeEach
    void init() {
        postUserDto = new PostUserDto("test", "email@fr.dom");
        putUserDto = new PutUserDto("new", "new@fr.dom");
        userEntity = new User();
        userEntity.setName("old");
        userEntity.setEmail("old@email.fr");

    }

    @Test
    void givenValidPostUser_whenToEntity_thenEmailIsNotBlank() {
        User userEntity = userMapper.toEntity(postUserDto);
        assertThat(userEntity.getEmail()).isNotBlank();
    }

    @Test
    void givenValidPostUser_whenToEntity_thenNameIsNotBlank() {
        User userEntity = userMapper.toEntity(postUserDto);
        assertThat(userEntity.getName()).isNotBlank();
    }

    @Test
    void givenValidPostUser_whenToEntity_thenIdIsNull() {
        User userEntity = userMapper.toEntity(postUserDto);
        assertThat(userEntity.getId()).isNull();
    }

    @Test
    void givenValidPostUser_whenToEntity_thenVotesArrayIsNull() {
        User userEntity = userMapper.toEntity(postUserDto);
        assertThat(userEntity.getVotes()).isNull();
    }

    @Test
    void givenValidPostUser_whenToEntity_thenCommentsArrayIsNull() {
        User userEntity = userMapper.toEntity(postUserDto);
        assertThat(userEntity.getVotes()).isNull();
    }

    @Test
    void giverNullNamePostUser_whenToEntity_thenNameIsNull() {
        postUserDto = new PostUserDto(null, "email@dc.fr");
        User userEntity = userMapper.toEntity(postUserDto);
        assertThat(userEntity.getName()).isNull();
    }

    @Test
    void giverNullEmailPostUser_whenToEntity_thenEmailIsNull() {
        postUserDto = new PostUserDto("test", null);
        User userEntity = userMapper.toEntity(postUserDto);
        assertThat(userEntity.getEmail()).isNull();
    }

    @Test
    void giverValidPutUser_whenToEntityForUpdate_thenEmailIsNotBlank() {
        userMapper.toEntity(userEntity, putUserDto);
        assertThat(userEntity.getEmail()).isNotBlank();
    }

    @Test
    void giverValidPutUser_whenToEntityForUpdate_thenNameIsEqual() {
        userMapper.toEntity(userEntity, putUserDto);
        assertThat(userEntity.getName()).isNotBlank();
    }

}