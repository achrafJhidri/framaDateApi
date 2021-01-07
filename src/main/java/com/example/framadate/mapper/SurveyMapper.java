package com.example.framadate.mapper;


import com.example.framadate.entity.Survey;
import com.example.framadate.model.survey_dtos.CreationSurveyDto;
import com.example.framadate.model.survey_dtos.PutSurveyDto;
import com.example.framadate.model.survey_dtos.SurveyDto;
import org.springframework.stereotype.Component;

@Component
public class SurveyMapper {
    public SurveyDto toDto(Survey survey) {
        return SurveyDto.builder()
                .id(survey.getId())
                .closed(survey.isClosed())
                .description(survey.getDescription())
                .name(survey.getName())
                .limitDate(survey.getLimitDate())
                .build();
    }


    public void toEntity(Survey survey, PutSurveyDto surveyDto) {
        if (surveyDto.getName() != null)
            survey.setName(surveyDto.getName());

        if (surveyDto.getDescription() != null)
            survey.setDescription(surveyDto.getDescription());

        if (surveyDto.getClose() != null)
            survey.setClosed(surveyDto.getClose());

        if (surveyDto.getLimitDate() != null)
            survey.setLimitDate(surveyDto.getLimitDate());
    }

    public Survey toEntity(CreationSurveyDto surveyDto) {

        return Survey.builder()
                .name(surveyDto.getName())
                .limitDate(surveyDto.getLimitDate())
                .description(surveyDto.getDescription())
                .build();
    }
}
