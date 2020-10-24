package com.example.framadate.service;

import com.example.framadate.entity.IResult;
import com.example.framadate.repository.SurveyRepository;

import com.example.framadate.repository.VoteRepository;

import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class ResultService {

    private final SurveyRepository surveyRepository;
    private final VoteRepository voteRepository;

    public ResultService(SurveyRepository surveyRepository, VoteRepository voteRepository) {
        this.surveyRepository = surveyRepository;
        this.voteRepository = voteRepository;
    }


    public HashMap<String, IResult> getResults(Long surveyId)  {
        var survey = surveyRepository.findById(surveyId);
        if ( survey.isEmpty() )
            throw new IllegalArgumentException("no survey Id with that Id");

        var result = new HashMap<String,IResult>();

        var availableOnlyResult = voteRepository.countVotesForSomeAvailability(surveyId,'A');
        var availableOrMaybeResult = voteRepository.countMaybeOrAvailableVotes(surveyId);

        if ( availableOnlyResult.isEmpty() )
            return result;

        result.put("only available votes",availableOnlyResult.stream().findFirst().get());
        result.put("available or maybe votes",availableOrMaybeResult.stream().findFirst().get()); //no need to check this because the first

        return result;
    }
}
