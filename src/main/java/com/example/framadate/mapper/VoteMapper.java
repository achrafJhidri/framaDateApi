package com.example.framadate.mapper;

import com.example.framadate.entity.Vote;
import com.example.framadate.entity.VoteId;
import com.example.framadate.model.vote_dtos.PostVoteDto;
import com.example.framadate.model.vote_dtos.PutVoteDto;
import com.example.framadate.model.vote_dtos.VoteDto;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    public final VoteIdMapper voteIdMapper;

    public VoteMapper(VoteIdMapper voteIdMapper) {
        this.voteIdMapper = voteIdMapper;
    }

    public VoteDto toDto(Vote vote) {
        return VoteDto.builder()
                .voteIdDto(voteIdMapper.toDto(vote.getVoteId()))
                .availability(vote.getAvailability())
                .comment(vote.getComment())
                .lastUpdate(vote.getLastUpdate())
                .votingDate(vote.getVotingDate())
                .build();
    }

    public Vote toEntity(PostVoteDto voteDto) {
        return Vote.builder()
                .availability(voteDto.getAvailability())
                .comment(voteDto.getComment())
                .voteId(VoteId.builder().build())
                .build();
    }

    public void toEntity(Vote vote, PutVoteDto voteDto) {
        if (voteDto.getComment() != null)
            vote.setComment(voteDto.getComment());
        if (voteDto.getAvailability() != null)
            vote.setAvailability(voteDto.getAvailability());
    }
}
