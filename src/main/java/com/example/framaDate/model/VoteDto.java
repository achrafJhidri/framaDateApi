package com.example.framadate.model;

import com.example.framadate.entity.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
    Date votingDate; // the date in which the user voted
    Date lastUpdate;
    String comment;
    Availability availability;
    VoteIdDto voteIdDto;
    public Long getUserId() {
        return voteIdDto.getUserId();
    }
    public Date getDateId() {
        return voteIdDto.getDateId();
    }

    public Long getSurveyId(){
        return voteIdDto.getSurveyId();
    }


}
