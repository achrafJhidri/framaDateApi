package com.example.framadate.model.userDtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUserDto {
    @Getter
    private String name;
    @Getter
    private String email;
}
