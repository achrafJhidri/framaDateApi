package com.example.framadate.service;

import com.example.framadate.entity.Survey;
import com.example.framadate.entity.User;
import com.example.framadate.entity.Vote;
import com.example.framadate.entity.VoteId;
import com.example.framadate.mapper.VoteMapper;
import com.example.framadate.model.voteDtos.PostVoteDto;
import com.example.framadate.model.voteDtos.PutVoteDto;
import com.example.framadate.model.voteDtos.VoteDto;
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
import java.util.stream.Stream;

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

    public VoteDto saveVote(User user, com.example.framadate.entity.Date date, Survey survey, PostVoteDto voteDto) {
        VoteId id = VoteId.builder()
                .dateId(date.getDate())
                .surveyId(survey.getId())
                .userId(user.getId())
                .build();
        val vote = voteRepository.findById(id);

        if (vote.isPresent())
            throw new IllegalArgumentException("you've already voted, do a put instead of post to update your vote");

        Vote voteEntity = voteMapper.toEntity(voteDto);
        voteEntity.setVotingDate(new Date());
        voteEntity.setDate(date);
        voteEntity.setUser(user);
        voteEntity.setSurvey(survey);
        voteEntity.setLastUpdate(voteEntity.getVotingDate());

        val result = voteRepository.save(voteEntity);
        return voteMapper.toDto(result);
    }
    public VoteDto vote(Long surveyId, PostVoteDto voteDto) {

        val survey = checkSurvey(surveyId);
        val user = checkUser(voteDto.getUserId());
        val stream = checkDate(survey, voteDto.getDateId());
        if(stream.count() == 0)
            throw new IllegalArgumentException("this date deosn't existe in this surveyChoices");
        return this.saveVote(user
                ,dateRepository.findById(voteDto.getDateId()).get() /*no need to check isPresent cz it wouldn't be inside
                                                                     the dates of the survey if it's not already in the db*/
                ,survey
                ,voteDto);
    }

    public VoteDto updateVote(Long surveyId, PutVoteDto voteDto) {
        VoteId id = VoteId.builder()
                .dateId(voteDto.getDateId())
                .surveyId(surveyId)
                .userId(voteDto.getUserId())
                .build();

        val vote = voteRepository.findById(id);

        if (vote.isEmpty())
            throw new IllegalArgumentException("not found Vote to update");
        voteMapper.toEntity(vote.get(), voteDto);
        vote.get().setLastUpdate(new Date());
        val result = voteRepository.save(vote.get());
        return voteMapper.toDto(result);
    }
    public List<VoteDto> findAll(Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if ( survey.isEmpty())
            throw new IllegalArgumentException("survey "+surveyId+" not found");
        return survey.map(value -> value.getVotes()
                .stream().map(voteMapper::toDto).collect(Collectors.toList())).orElse(null);
    }

    private Survey checkSurvey(Long surveyId) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isEmpty()) { //Not Found in db
            throw new IllegalArgumentException("survey deosn't exist");
        }
        if (surveyOptional.get().getLimitDate().compareTo(new Date()) < 0)
            throw new IllegalArgumentException("this survey's limit date for voting is reached");

        if (surveyOptional.get().getClosed())
            throw new IllegalArgumentException("voting is closed for this survey");
        return surveyOptional.get();
    }
    private User checkUser(Long userId) {
        val user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new IllegalArgumentException("the user "+userId+" doesn't exist");

        return user.get();
    }
    private Stream<com.example.framadate.entity.Date> checkDate(Survey survey, Date dateId) {
        return survey.getDates().stream()
                .filter(date -> date.getDate().compareTo(dateId) == 0);
    }


}
