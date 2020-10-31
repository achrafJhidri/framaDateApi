package com.example.framadate.model.surveyDtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SurveyDatesDto {
    List<Date> surveyDates;
    String message;
}
