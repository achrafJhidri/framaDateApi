package com.example.framadate.model.user_dtos;

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
    @Size(message = "the name must have at least 2 characters", min = 2)
    private String name;
    @Getter
    @Email(message = "the email must be syntactically correct")
    private String email;
}
