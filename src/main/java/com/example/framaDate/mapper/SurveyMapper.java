package com.example.framadate.mapper;


import com.example.framadate.entity.Survey;
import com.example.framadate.entity.User;
import com.example.framadate.model.surveyDtos.CreationSurveyDto;
import com.example.framadate.model.surveyDtos.SurveyDto;
import org.springframework.stereotype.Component;

@Component
public class SurveyMapper {
    public SurveyDto toDto(Survey survey){
        return SurveyDto.builder()
                .id(survey.getId()  )
                .closed(survey.getClosed())
                .description(survey.getDescription())
                .name(survey.getName())
                .limitDate(survey.getLimitDate())
                .build();
    }


    public void toEntity(      Survey survey, SurveyDto surveyDto){
        if ( surveyDto.getName() != null)
            survey.setName(surveyDto.getName() );

        if ( surveyDto.getDescription() != null)
            survey.setDescription(surveyDto.getDescription());

        if (surveyDto.getClosed() != null )
            survey.setClosed(surveyDto.getClosed());

        if(surveyDto.getLimitDate() != null)
            survey.setLimitDate(surveyDto.getLimitDate());
    }

    public Survey toEntity(CreationSurveyDto surveyDto){
        if(UserMapper.isNullOrEmpty(surveyDto.getName())
                || UserMapper.isNullOrEmpty(surveyDto.getDescription())
                || surveyDto.getLimitDate() == null )
            throw new IllegalArgumentException("empty fields in surveyDto");

        return Survey.builder()
                .name(surveyDto.getName())
                .limitDate(surveyDto.getLimitDate())
                .description(surveyDto.getDescription())
                .build();
    }
}