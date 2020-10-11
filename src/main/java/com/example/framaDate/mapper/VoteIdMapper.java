package com.example.framadate.mapper;


import com.example.framadate.entity.VoteId;

import com.example.framadate.model.voteDtos.VoteIdDto;
import org.springframework.stereotype.Component;



@Component
public class VoteIdMapper {


    public VoteIdDto toDto(VoteId voteId) {
        return VoteIdDto.builder()
                .dateId(voteId.getDateId())
                .surveyId(voteId.getSurveyId())
                .userId(voteId.getUserId())
                .build();
    }
}