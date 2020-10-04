package com.example.framadate.controller;

import com.example.framadate.model.UserDto;
import com.example.framadate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/framadate/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/")
    public List<UserDto> all(){
        return  userService.findAllDtos();
    }
    @GetMapping(value="/{id}")
    public UserDto findOne(@PathVariable Long id){
        return userService.findUserDtoById(id);
    }
    @PostMapping(value="/")
    public UserDto create(@RequestBody UserDto  user){
        return userService.save(user);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id,@RequestBody UserDto user){
        return Optional
                .ofNullable( userService.updateOne(id,user) )
                .map( surveyDtoUpdated -> ResponseEntity.ok().body(surveyDtoUpdated) ) //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );   //404 Not found
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return Optional
                .ofNullable(userService.deleteUser(id))
                .map(user -> ResponseEntity.ok().body("the user "+user+"  has been deleted"))
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body("the userId"+id+" doesn't match any user"));
    }

}
