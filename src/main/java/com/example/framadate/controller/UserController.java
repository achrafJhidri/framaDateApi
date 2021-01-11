package com.example.framadate.controller;

import com.example.framadate.model.user_dtos.PostUserDto;
import com.example.framadate.model.user_dtos.PutUserDto;
import com.example.framadate.model.user_dtos.UserDto;
import com.example.framadate.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/framadate/api/v1/users")
@Api(description = "User Resource REST Endpoint")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "returns all the users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
    })
    @GetMapping(value = "/")
    public List<UserDto> all() {
        return userService.findAllUsers();
    }

    @ApiOperation(value = "returns one user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested user wasn't found"),
    })
    @GetMapping(value = "/{userId}")
    public UserDto findOne(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @ApiOperation(value = "creates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 400, message = "invalid model"),
    })
    @PostMapping(value = "/")
    public ResponseEntity<UserDto> create(@Valid @RequestBody PostUserDto user) {
        var userDto = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @ApiOperation(value = "creates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 400, message = "invalid model"),
            @ApiResponse(code = 404, message = "The requested user wasn't found")
    })
    @PutMapping(value = "/{userId}")
    public UserDto update(@PathVariable Long userId, @Valid @RequestBody PutUserDto user) {
        return userService.update(userId, user);
    }

    @ApiOperation(value = "deletes a user (not ready yet)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 400, message = "invalid model"),
            @ApiResponse(code = 404, message = "The requested user wasn't found")
    })
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId) {
        return Optional
                .ofNullable(userService.deleteUser(userId))
                .map(user -> ResponseEntity.ok().body("the user " + user + "  has been deleted"))
                .orElseThrow();
    }

}
