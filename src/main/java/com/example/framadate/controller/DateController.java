package com.example.framadate.controller;

import com.example.framadate.model.survey_dtos.SurveyDatesDto;
import com.example.framadate.service.DateService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class DateController {
    private final DateService dateService;

    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping(value = "/{surveyId}/dates")
    public List<Date> dates(@PathVariable Long surveyId) {
        return dateService.getAllDates(surveyId);
    }

    @PostMapping(value = "/{surveyId}/dates")
    public List<Date> addDates(@PathVariable Long surveyId, @RequestBody Set<Date> dates) {
        return dateService.addDates(surveyId, dates);

    }

    @DeleteMapping(value = "/{surveyId}/dates/{dateId}")
    public SurveyDatesDto deleteDate(@PathVariable Long surveyId, @PathVariable String dateId) throws ParseException {
        return dateService.deleteDate(surveyId, dateId);
    }

}
