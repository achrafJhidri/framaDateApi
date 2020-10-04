package com.example.framadate.controller;

import com.example.framadate.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class DateController {
    private final SurveyService surveyService;

    public DateController(SurveyService surveyService){
        this.surveyService = surveyService;
    }
    @GetMapping(value="/{id}/dates")
    public ResponseEntity<List<Date>> dates(@PathVariable Long id){
        return Optional
                .ofNullable(surveyService.getAllDates(id))
                .map(dates ->ResponseEntity.ok().body(dates) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PostMapping(value="/{surveyId}/dates")
    public ResponseEntity<List<Date>> addDates(@PathVariable Long surveyId,@RequestBody Set<Date> dates){
        return Optional
                .ofNullable(surveyService.addDates(surveyId,dates))
                .map(dateList ->ResponseEntity.ok().body(dateList) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @DeleteMapping(value="/{surveyId}/dates/{dateId}")
    public ResponseEntity<String> deleteDate(@PathVariable Long surveyId,@PathVariable  String dateId) throws ParseException {
        return Optional
                .ofNullable(surveyService.deleteDate(surveyId,new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(dateId)))
                .map(survey ->ResponseEntity.ok().body("the date "+dateId+" has been deleted from the survey "+surveyId) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("the date "+dateId+" not found in survey "+surveyId));
    }

}
