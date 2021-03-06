package com.example.framadate.service;

import com.example.framadate.entity.IGenericResult;
import com.example.framadate.exceptions.NotFoundException;
import com.example.framadate.repository.SurveyRepository;
import com.example.framadate.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.framadate.model.Constants.SURVEY;

@Service
public class ResultService {

    private final SurveyRepository surveyRepository;
    private final VoteRepository voteRepository;

    public ResultService(SurveyRepository surveyRepository, VoteRepository voteRepository) {
        this.surveyRepository = surveyRepository;
        this.voteRepository = voteRepository;
    }


    public Map<String, IGenericResult> getResults(Long surveyId) {
        var survey = surveyRepository.findById(surveyId);
        if (survey.isEmpty())
            throw new NotFoundException(SURVEY + surveyId);

        var result = new HashMap<String, IGenericResult>();


        var aResult = voteRepository.countAvailability(surveyId, 'A');
        var mResult = voteRepository.countAvailability(surveyId, 'M');
        var nResult = voteRepository.countAvailability(surveyId, 'N');

        result.put("number of Available votes all dates combined", aResult);
        result.put("number of NotAvailable votes all dates combined", mResult);
        result.put("number of Maybe vote all dates combined", nResult);


        var availableOnlyResult = voteRepository.countVotesForSomeAvailability(surveyId, 'A');
        var availableOrMaybeResult = voteRepository.countMaybeOrAvailableVotes(surveyId);

        if (availableOrMaybeResult.stream().findFirst().isPresent())
            result.put("available or maybe votes", availableOrMaybeResult.stream().findFirst().get());
        if (availableOnlyResult.stream().findFirst().isPresent()) {

            result.put("only available votes", availableOnlyResult.stream().findFirst().get());
        }

        return result;
    }
}
