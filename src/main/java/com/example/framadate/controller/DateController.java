package com.example.framadate.controller;

import com.example.framadate.model.survey_dtos.SurveyDatesDto;
import com.example.framadate.service.DateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
@Api(tags = {"Survey's dates Resource REST Endpoint"})
public class DateController {
    private final DateService dateService;

    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @ApiOperation(value = "returns the possible dates for the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
    })
    @GetMapping(value = "/{surveyId}/dates")
    public List<Date> dates(@PathVariable Long surveyId) {
        return dateService.getAllDates(surveyId);
    }

    @ApiOperation(value = "add new dates to the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
    })
    @PostMapping(value = "/{surveyId}/dates")
    public List<Date> addDates(@PathVariable Long surveyId, @RequestBody Set<Date> dates) {
        return dateService.addDates(surveyId, dates);
    }

    @ApiOperation(value = "remove the date given from the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 400, message = "not well parsed date parameter"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
    })
    @DeleteMapping(value = "/{surveyId}/dates/{dateId}")
    public SurveyDatesDto deleteDate(@PathVariable Long surveyId, @PathVariable String dateId) throws ParseException {
        return dateService.deleteDate(surveyId, dateId);
    }

}
