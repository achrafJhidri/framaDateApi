package com.example.framadate.mapper;

import com.example.framadate.entity.Vote;
import com.example.framadate.model.VoteDto;
import org.springframework.stereotype.Component;


@Component
public class VoteMapper {

    public final VoteIdMapper voteIdMapper;

    public VoteMapper(VoteIdMapper voteIdMapper) {
        this.voteIdMapper = voteIdMapper;
    }

    public VoteDto toDto(Vote vote){
        return VoteDto.builder()
                .voteIdDto(voteIdMapper.toDto(vote.getVoteId()))
                .availability(vote.getAvailability())
                .comment(vote.getComment())
                .lastUpdate(vote.getLastUpdate())
                .votingDate(vote.getVotingDate())
                .build();
    }

    public Vote toEntity(VoteDto voteDto) {
        return Vote.builder()
                .availability(voteDto.getAvailability())
                .comment(voteDto.getComment())
                .voteId(voteIdMapper.toEntity(voteDto.getVoteIdDto()))
                .build();
    }
}
