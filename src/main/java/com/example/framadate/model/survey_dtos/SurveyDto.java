package com.example.framadate.model.survey_dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {
    Long id;
    String name;
    String description;
    Date limitDate;
    Boolean closed;
}
