package com.example.framadate.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;
    private Date creationDate;
    private Date lastUpdate;
    private UserDto user;
    private SurveyDto survey;
}
