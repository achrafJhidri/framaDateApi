package com.example.framadate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateDto {
    Long id;
    SurveyDto survey;
    Date date;
    Set<VoteDto> votes;
}
