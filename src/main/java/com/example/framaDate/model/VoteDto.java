package com.example.framadate.model;

import com.example.framadate.entity.Availability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
    Date votingDate; // the date in which the user voted
    Date lastUpdate;
    String comment;
    Availability availability;
    DateDto date;    // the survey's date
}
