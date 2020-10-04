package com.example.framadate.service;

import com.example.framadate.entity.Survey;
import com.example.framadate.entity.User;
import com.example.framadate.entity.Vote;
import com.example.framadate.entity.VoteId;
import com.example.framadate.mapper.VoteMapper;
import com.example.framadate.model.VoteDto;
import com.example.framadate.repository.VoteRepository;
import lombok.val;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class VoteService {

    private final VoteMapper voteMapper;
    private final VoteRepository voteRepository;
    private final DateService dateService;


    public VoteService(VoteMapper voteMapper, VoteRepository voteRepository, DateService dateService) {
        this.voteMapper = voteMapper;
        this.voteRepository = voteRepository;
        this.dateService = dateService;
    }

    public VoteDto vote(User user, Date date,Survey survey, VoteDto voteDto) {
        VoteId id = VoteId.builder()
                .dateId(date)
                .surveyId(survey.getId())
                .userId(user.getId())
                .build();



        val vote = voteRepository.findById( id);
        Vote voteEntity ;
        if ( vote.isPresent())
        { //then this is an update of the vote
          //votingDate and voteId should remain the same
            if(voteDto.getComment() != null)
                vote.get().setComment(voteDto.getComment());
            if(voteDto.getAvailability() != null)
                vote.get().setAvailability(voteDto.getAvailability());
            voteEntity=vote.get();
        }else
        { //

            voteEntity=voteMapper.toEntity(voteDto);

            voteEntity.setVotingDate(new Date());
            val date1 =dateService.findbyId(date);
            voteEntity.setDate(date1);
            voteEntity.setUser(user);
            voteEntity.setSurvey(survey);
        }

        voteEntity.setLastUpdate(new Date());
        val result = voteRepository.save(voteEntity);
        return voteMapper.toDto(result) ;
    }

    public VoteDto toDtos(Vote vote) {
        return voteMapper.toDto(vote);
    }
}
