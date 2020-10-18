package com.example.framadate.controller;

import com.example.framadate.model.surveyDtos.CreationSurveyDto;
import com.example.framadate.model.surveyDtos.PutSurveyDto;
import com.example.framadate.model.surveyDtos.SurveyDto;
import com.example.framadate.model.userDtos.PutUserDto;
import com.example.framadate.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService){
        this.surveyService = surveyService;
    }


    @GetMapping(value = "/")
    public ResponseEntity<List<SurveyDto>> surveys(){
        return  ResponseEntity.ok().body( surveyService.findAllSurveys());
    }
    @PostMapping(value="/")
    public ResponseEntity<SurveyDto> addSurvey(@Valid @RequestBody CreationSurveyDto surveyDto ){
        SurveyDto survey = surveyService.createSurvey(surveyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(survey);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<SurveyDto> findOne(@PathVariable Long id) {
        return Optional
                .ofNullable( surveyService.findOneSurvey(id) )
                .map( surveyDto -> ResponseEntity.ok().body(surveyDto) ) //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );   //404 Not found
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<SurveyDto> update(@PathVariable Long id ,@Valid @RequestBody PutSurveyDto surveyDto){

       return  Optional
                .ofNullable( surveyService.updateSurvey(id,surveyDto) )
                .map( surveyDtoUpdated -> ResponseEntity.ok().body(surveyDtoUpdated) ) //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );   //404 Not found

    }
}
