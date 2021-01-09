package com.example.framadate.service;

import com.example.framadate.entity.Date;
import com.example.framadate.entity.Survey;
import com.example.framadate.entity.User;
import com.example.framadate.mapper.VoteMapper;
import com.example.framadate.model.vote_dtos.PostVoteDto;
import com.example.framadate.repository.DateRepository;
import com.example.framadate.repository.SurveyRepository;
import com.example.framadate.repository.UserRepository;
import com.example.framadate.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
    @Mock
    private VoteMapper voteMapper;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private DateRepository dateRepository;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private VoteService voteService;

    @Mock
    private Survey survey;
    @Mock
    private User user;
    @Mock
    private Stream<Date> surveyDates;
    @Mock
    private Date votingDate;


    @Test
    void givenValidVote_whenVote_thenOk() {
        when(voteService.checkSurvey(any(Long.class))).thenReturn(survey);
        when(voteService.checkUser(any(Long.class))).thenReturn(user);
        when(voteService.checkDate(survey, any(java.util.Date.class))).thenReturn(surveyDates);
        when(dateRepository.findById(any(java.util.Date.class))).thenReturn(Optional.of(votingDate));

        voteService.vote(any(Long.class), any(PostVoteDto.class));

        verify(voteService, times(1)).checkSurvey(any(Long.class));
        verify(voteService, times(1)).checkUser(any(Long.class));
        verify(voteService, times(1)).checkDate(survey, any(java.util.Date.class));
        verify(dateRepository, times(1)).findById(any(java.util.Date.class));
    }


}
