package com.example.framadate.model;



import java.util.Date;

public class CommentDto {
    private Long id;
    private String comment;
    private Date creationDate;
    private Date lastUpdate;
    private UserDto user;
    private SurveyDto survey;
}
