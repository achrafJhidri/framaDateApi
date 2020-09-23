package com.example.framadate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


public class DateDto {
    private Date date;
    private Set<VoteDto> votes;

}
