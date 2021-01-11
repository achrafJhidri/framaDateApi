package com.example.framadate.service;

import com.example.framadate.entity.Survey;
import com.example.framadate.mapper.SurveyMapper;
import com.example.framadate.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {
    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyMapper surveyMapper;

    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private List<Survey> surveyList;

    @Mock
    private Survey survey;

    @Test
    void given_whenFindAll_thenOk() {

        when(surveyRepository.findAll()).thenReturn(surveyList);

        surveyService.findAllSurveys();

        verify(surveyRepository, times(1)).findAll();
        verify(surveyMapper, times(0)).toDto(any(Survey.class));
    }

//    @Test
//    void given_whenFindOneSurvey_thenOk() {
//        var opt = Optional.of(survey);
//        when(surveyRepository.findById(any(Long.class))).thenReturn(opt);
//
//        surveyService.findOneSurvey(any(Long.class));
//
//        verify(surveyRepository, times(1)).findById(any(Long.class));
//        verify(surveyMapper, times(0)).toDto(opt.get());
//    }
}
