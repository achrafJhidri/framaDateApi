package com.example.framadate.controller;

import com.example.framadate.model.survey_dtos.CreationSurveyDto;
import com.example.framadate.model.survey_dtos.PutSurveyDto;
import com.example.framadate.model.survey_dtos.SurveyDto;
import com.example.framadate.service.SurveyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/framadate/api/v1/surveys")
@Api(tags = {"Survey Resource REST Endpoint"})
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @ApiOperation(value = "returns all the surveys")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
    })
    @GetMapping(value = "/")
    public List<SurveyDto> surveys() {
        return surveyService.findAllSurveys();
    }

    @ApiOperation(value = "creates a survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 400, message = "invalid model"),
    })
    @PostMapping(value = "/")
    public ResponseEntity<SurveyDto> addSurvey(@Valid @RequestBody CreationSurveyDto surveyDto) {
        SurveyDto survey = surveyService.createSurvey(surveyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(survey);
    }

    @ApiOperation(value = "returns one survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
    })
    @GetMapping(value = "/{id}")
    public SurveyDto findOne(@PathVariable Long id) {
        return surveyService.findOneSurvey(id);
    }

    @ApiOperation(value = "updates a survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 400, message = "invalid model"),
    })
    @PutMapping(value = "/{id}")
    public SurveyDto update(@PathVariable Long id, @Valid @RequestBody PutSurveyDto surveyDto) {

        return surveyService.updateSurvey(id, surveyDto);

    }
}
