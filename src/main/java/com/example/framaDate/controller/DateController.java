package com.example.framadate.controller;

import com.example.framadate.service.DateService;
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
    private final DateService dateService;

    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping(value = "/{id}/dates")
    public ResponseEntity<List<Date>> dates(@PathVariable Long id) {
        return Optional
                .ofNullable(dateService.getAllDates(id))
                .map(dates -> ResponseEntity.ok().body(dates))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(value = "/{surveyId}/dates")
    public ResponseEntity<List<Date>> addDates(@PathVariable Long surveyId, @RequestBody Set<Date> dates) {
        return Optional
                .ofNullable(dateService.addDates(surveyId, dates))
                .map(dateList -> ResponseEntity.ok().body(dateList))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping(value = "/{surveyId}/dates/{dateId}")
    public SurveyDatesDto deleteDate(@PathVariable Long surveyId, @PathVariable String dateId) throws ParseException {
        return dateService.deleteDate(surveyId, dateId);
    }

}
