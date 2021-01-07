package com.example.framadate.model.vote_dtos;

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
    Date votingDate;
    Date lastUpdate;
    String comment;
    Availability availability;
    VoteIdDto voteIdDto;
}
