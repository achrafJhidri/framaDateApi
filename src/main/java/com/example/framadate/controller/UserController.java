package com.example.framadate.controller;

import com.example.framadate.model.user_dtos.PostUserDto;
import com.example.framadate.model.user_dtos.PutUserDto;
import com.example.framadate.model.user_dtos.UserDto;
import com.example.framadate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/framadate/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public List<UserDto> all() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{userId}")
    public UserDto findOne(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping(value = "/")
    public ResponseEntity<UserDto> create(@Valid @RequestBody PostUserDto user) {
        var userDto = userService.create(user);
       return  ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping(value = "/{userId}")
    public UserDto update(@PathVariable Long userId, @Valid @RequestBody PutUserDto user) {
        return userService.update(userId, user);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId) {
        return Optional
                .ofNullable(userService.deleteUser(userId))
                .map(user -> ResponseEntity.ok().body("the user " + user + "  has been deleted"))
                .orElseThrow();
    }

}
