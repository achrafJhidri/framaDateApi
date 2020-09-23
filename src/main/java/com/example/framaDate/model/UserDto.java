package com.example.framadate.model;



import java.util.Set;


public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Set<VoteDto> votes;
    private Set<CommentDto> comments;
}
