package com.example.framadate.service;

import com.example.framadate.entity.Survey;
import com.example.framadate.entity.User;
import com.example.framadate.entity.Vote;
import com.example.framadate.entity.VoteId;
import com.example.framadate.mapper.VoteMapper;
import com.example.framadate.model.VoteDto;
import com.example.framadate.repository.DateRepository;
import com.example.framadate.repository.SurveyRepository;
import com.example.framadate.repository.UserRepository;
import com.example.framadate.repository.VoteRepository;
import lombok.val;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final VoteMapper voteMapper;
    private final VoteRepository voteRepository;
    private final DateRepository dateRepository;
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;


    public VoteService(VoteMapper voteMapper, VoteRepository voteRepository, DateRepository dateRepository,
                       SurveyRepository surveyRepository, UserRepository userRepository) {
        this.voteMapper = voteMapper;
        this.voteRepository = voteRepository;
        this.dateRepository = dateRepository;
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
    }

    public VoteDto vote(User user, com.example.framadate.entity.Date date, Survey survey, VoteDto voteDto) {
        VoteId id = VoteId.builder()
                .dateId(date.getDate())
                .surveyId(survey.getId())
                .userId(user.getId())
                .build();



        val vote = voteRepository.findById( id);
        Vote voteEntity ;
        if ( vote.isPresent())
        { //then this is just an update of the vote
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
            voteEntity.setDate(date);
            voteEntity.setUser(user);
            voteEntity.setSurvey(survey);
        }

        voteEntity.setLastUpdate(new Date());
        val result = voteRepository.save(voteEntity);
        return voteMapper.toDto(result) ;
    }

    public VoteDto vote(Long surveyId, VoteDto voteDto)  {

        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isEmpty()) { //Not Found in db
            return null ; //TODO throw notFoundException
        }
        voteDto.getVoteIdDto().setSurveyId(surveyId); //TODO remove when creating the adapted VoteDto


        val user = userRepository.findById(voteDto.getUserId());
        if (user.isEmpty())
            return null; //TODO throw notFoundException

        val stream = surveyOptional.get().getDates().stream()
                .filter(date-> date.getDate().compareTo(voteDto.getDateId())==0); //even if it's a stream at most there will be 1date matching
        if(stream.count() > 0) {
            return this.vote(user.get()
                            ,dateRepository.findById(voteDto.getDateId()).get() /*no need to check isPresent cz it wouldn't be inside
                                                                                the dates of the survey if it it didn't exist in the db*/
                             ,surveyOptional.get(), voteDto);
        }
        return null; //TODO throw notFoundException


    }

    public List<VoteDto> findAll(Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId) ;
        return survey.map(value -> value.getVotes() //TODO throw notFoundException
                .stream().map(voteMapper::toDto).collect(Collectors.toList())).orElse(null);
    }
}
