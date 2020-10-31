package com.example.framadate.model.userDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutUserDto {
    @Getter
    @Size(min = 2)
    private String name;
    @Getter
    @Email
    private String email;
}
