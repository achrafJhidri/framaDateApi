package com.example.framadate.service;


import com.example.framadate.entity.Comment;
import com.example.framadate.entity.Survey;
import com.example.framadate.entity.User;

import com.example.framadate.entity.Vote;
import com.example.framadate.mapper.SurveyMapper;
import com.example.framadate.model.CommentDto;
import com.example.framadate.model.SurveyDto;

import com.example.framadate.model.VoteDto;
import com.example.framadate.repository.SurveyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.apache.tomcat.util.json.JSONParser;
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
    public SurveyDto createSurvey(SurveyDto surveyDto) {
        Survey survey = new Survey();
        surveyMapper.toEntity(survey,surveyDto);
        survey = surveyRepository.saveAndFlush(survey);//will generate a new id
        surveyDto.setId(survey.getId());

       return surveyDto;
    }
    public SurveyDto updateSurvey(Long id, SurveyDto surveyDto) {
        Optional<Survey> surveyOptional = surveyRepository.findById(id);
        if (surveyOptional.isEmpty()) { //Not Found in db
          return null ;
        }
        Survey survey  = surveyOptional.get(); // this isn't redundant
                                               // it's useful when you want to update juste one field
                                               // so all the others must remain as before
        surveyMapper.toEntity(survey , surveyDto);
        surveyRepository.saveAndFlush(survey);
        return surveyMapper.toDto(survey);
    }



}
