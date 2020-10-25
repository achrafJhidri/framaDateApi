package com.example.framadate.service;


import com.example.framadate.entity.Survey;
import com.example.framadate.exceptions.NotFoundException;
import com.example.framadate.mapper.SurveyMapper;
import com.example.framadate.model.surveyDtos.CreationSurveyDto;
import com.example.framadate.model.surveyDtos.PutSurveyDto;
import com.example.framadate.model.surveyDtos.SurveyDto;
import com.example.framadate.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public SurveyService(SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;

    }

    public List<SurveyDto> findAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        return surveys.stream().map(surveyMapper::toDto).collect(Collectors.toList());
    }

    public SurveyDto findOneSurvey(Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        return survey.map(surveyMapper::toDto).orElseThrow(() -> new NotFoundException("survey " + surveyId));
    }

    public SurveyDto createSurvey(CreationSurveyDto surveyDto) {
        Survey survey = surveyMapper.toEntity(surveyDto);
        survey.setClosed(false); //a new survey is not closed


        survey = surveyRepository.saveAndFlush(survey);

        return surveyMapper.toDto(survey);
    }

    public SurveyDto updateSurvey(Long surveyId, PutSurveyDto surveyDto) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isEmpty()) { //Not Found in db
            throw new NotFoundException("survey " + surveyId);
        }
        Survey survey = surveyOptional.get(); // this isn't redundant
        // it's useful when you want to update juste one field
        // so all the others must remain as before
        surveyMapper.toEntity(survey, surveyDto);
        surveyRepository.saveAndFlush(survey);
        return surveyMapper.toDto(survey);
    }


}
