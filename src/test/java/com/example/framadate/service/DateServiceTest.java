package com.example.framadate.service;

import com.example.framadate.entity.Survey;
import com.example.framadate.mapper.DateMapper;
import com.example.framadate.repository.DateRepository;
import com.example.framadate.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DateServiceTest {
    @Mock
    private DateRepository dateRepository;

    @Mock
    private DateMapper dateMapper;

    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    private DateService dateService;

    @Mock
    private Survey survey;

    @Mock
    private Set<Date> dates;


    @Test
    void givenValidDates_whenAddDates_thenOk() {
        Optional<Survey> surveyOpt = Optional.of(survey);

        when(surveyRepository.findById(1L)).thenReturn(surveyOpt);
        when(surveyRepository.saveAndFlush(surveyOpt.get())).then(returnsFirstArg());

        dateService.addDates(1L, this.dates);

        verify(surveyRepository, times(1)).findById(1L);
        verify(surveyRepository, times(1)).saveAndFlush(surveyOpt.get());
    }


    @Test
    void givenValidDate_whenDeleteDate_thenOk() throws ParseException {
        Optional<Survey> surveyOpt = Optional.of(survey);

        String dateId = "2200-10-02T13:14:59.000+00:00";

        when(surveyRepository.findById(1L)).thenReturn(surveyOpt);
        when(surveyRepository.saveAndFlush(surveyOpt.get())).then(returnsFirstArg());
        when(survey.removeDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(dateId))).thenReturn(true);

        dateService.deleteDate(1L, dateId);

        verify(surveyRepository, times(1)).findById(1L);
        verify(surveyRepository, times(1)).saveAndFlush(surveyOpt.get());
    }

}
