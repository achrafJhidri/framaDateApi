package com.example.framadate.model.userDtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUserDto {
    @Getter
    @NotBlank(message = "name mustn't be empty")
    @Size(min = 2,max = 15)
    private String name;
    @Getter
    @NotBlank(message = "email must be a valid email")
    @Email
    private String email;
}
