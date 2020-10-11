package com.example.framadate.model.surveyDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreationSurveyDto {
    String name;
    String description;
    Date limitDate;
}
