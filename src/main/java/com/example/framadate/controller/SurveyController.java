package com.example.framadate.controller;

import com.example.framadate.model.surveyDtos.CreationSurveyDto;
import com.example.framadate.model.surveyDtos.PutSurveyDto;
import com.example.framadate.model.surveyDtos.SurveyDto;
import com.example.framadate.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }


    @GetMapping(value = "/")
    public List<SurveyDto> surveys() {
        return surveyService.findAllSurveys();
    }

    @PostMapping(value = "/")
    public ResponseEntity<SurveyDto> addSurvey(@Valid @RequestBody CreationSurveyDto surveyDto) {
        SurveyDto survey = surveyService.createSurvey(surveyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(survey);
    }

    @GetMapping(value = "/{id}")
    public SurveyDto findOne(@PathVariable Long id) {
        return surveyService.findOneSurvey(id);
    }

    @PutMapping(value = "/{id}")
    public SurveyDto update(@PathVariable Long id, @Valid @RequestBody PutSurveyDto surveyDto) {

        return surveyService.updateSurvey(id, surveyDto);

    }
}
