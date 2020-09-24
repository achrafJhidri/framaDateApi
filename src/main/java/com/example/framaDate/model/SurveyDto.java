package com.example.framadate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {
    Long id;
    String name;
    String description;
    Date limitDate;
    Boolean closed;
    Set<DateDto> dates;
    Set<CommentDto> comments;
}
