package com.example.framadate.service;


import com.example.framadate.entity.Survey;

import com.example.framadate.mapper.SurveyMapper;
import com.example.framadate.model.surveyDtos.CreationSurveyDto;
import com.example.framadate.model.surveyDtos.SurveyDto;

import com.example.framadate.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public SurveyService(SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;

    }

    public List<SurveyDto> findAllSurveys(){
        List<Survey> surveys = surveyRepository.findAll();
        return  surveys.stream().map(surveyMapper::toDto).collect(Collectors.toList());
    }
    public SurveyDto findOneSurvey(Long id){
        Optional<Survey> survey = surveyRepository.findById(id) ;
        return survey.map(surveyMapper::toDto).orElse(null);
    }
    public SurveyDto createSurvey(CreationSurveyDto surveyDto) {
        Survey survey = surveyMapper.toEntity(surveyDto);
        survey.setClosed(false); //a new survey is not closed
        survey = surveyRepository.saveAndFlush(survey);//will generate a new id

        return surveyMapper.toDto(survey);
    }
    public SurveyDto updateSurvey(Long id, SurveyDto surveyDto) {
        Optional<Survey> surveyOptional = surveyRepository.findById(id);
        if (surveyOptional.isEmpty()) { //Not Found in db
          return null ;//TODO throw exception Here
        }
        Survey survey  = surveyOptional.get(); // this isn't redundant
                                               // it's useful when you want to update juste one field
                                               // so all the others must remain as before
        surveyMapper.toEntity(survey , surveyDto);
        surveyRepository.saveAndFlush(survey);
        return surveyMapper.toDto(survey);
    }



}
