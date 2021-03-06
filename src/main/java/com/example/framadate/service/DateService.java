package com.example.framadate.service;

import com.example.framadate.entity.Date;
import com.example.framadate.entity.Survey;
import com.example.framadate.exceptions.NotAllowedException;
import com.example.framadate.exceptions.NotFoundException;
import com.example.framadate.mapper.DateMapper;
import com.example.framadate.model.survey_dtos.SurveyDatesDto;
import com.example.framadate.repository.DateRepository;
import com.example.framadate.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.framadate.model.Constants.SURVEY;

@Service
public class DateService {

    private final DateRepository dateRepository;
    private final DateMapper dateMapper;
    private final SurveyRepository surveyRepository;

    public DateService(DateRepository dateRepository, DateMapper dateMapper, SurveyRepository surveyRepository) {
        this.dateRepository = dateRepository;
        this.dateMapper = dateMapper;
        this.surveyRepository = surveyRepository;
    }


    public List<java.util.Date> toDtos(Set<Date> dateEntities) {
        return dateMapper.toDtos(dateEntities);
    }

    public Set<Date> getOrCreateDates(Set<java.util.Date> dates) {
        return dates.stream().map(date -> {
                    Optional<Date> optDate = dateRepository.findById(date);
            return optDate.orElse(dateRepository.saveAndFlush(Date.builder().theDate(date).build()));
                }
        ).collect(Collectors.toSet());
    }

    public List<java.util.Date> getAllDates(Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        return survey.map(value -> value.getDates()
                .stream().map(com.example.framadate.entity.Date::getTheDate)
                .collect(Collectors.toList()))
                .orElseThrow(() -> new NotFoundException(SURVEY + surveyId));
    }

    public List<java.util.Date> addDates(Long surveyId, Set<java.util.Date> dates) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);

        if (survey.isEmpty()) {
            throw new NotFoundException(SURVEY + surveyId);
        }

        var datesFiltrated = filterDates(dates, survey.get().getLimitDate());
        Set<com.example.framadate.entity.Date> dateEntities = this.getOrCreateDates(datesFiltrated);
        survey.get().addDates(dateEntities);
        surveyRepository.saveAndFlush(survey.get());

        return this.toDtos(dateEntities);
    }

    private static Set<java.util.Date> filterDates(Set<java.util.Date> dates, java.util.Date limitDate) {
        return dates.stream().filter(date -> {
            if (date != null)
                return date.compareTo(new java.util.Date()) > 0 && date.compareTo(limitDate) > 0;
            return false;
        }).collect(Collectors.toSet());
    }

    public SurveyDatesDto deleteDate(Long surveyId, String dateId) throws ParseException {
        java.util.Date dateParsed;
        try {
            dateParsed = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(dateId);
        } catch (ParseException e) {
            throw new ParseException("the format yyyy-MM-dd'T'HH:mm:ss.SSSXXX is not respected try something like 3000-12-29T15:01:20.000+00:00", e.getErrorOffset());
        }

        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isEmpty()) {
            throw new NotFoundException(SURVEY + surveyId);
        }
        boolean retrieved = survey.get().removeDate(dateParsed);
        if (retrieved) {
            surveyRepository.saveAndFlush(survey.get());
            SurveyDatesDto result = new SurveyDatesDto();
            result.setSurveyDates(this.toDtos(survey.get().getDates()));
            result.setMessage("date " + dateId + " got deleted");
            return result;
        }
        throw new NotAllowedException("date " + dateId + " not found in survey " + surveyId);
    }

}
