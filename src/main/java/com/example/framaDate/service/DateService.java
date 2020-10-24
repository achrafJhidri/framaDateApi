package com.example.framadate.service;

import com.example.framadate.entity.Date;
import com.example.framadate.entity.Survey;
import com.example.framadate.mapper.DateMapper;
import com.example.framadate.repository.DateRepository;
import com.example.framadate.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
                    return optDate.orElse(dateRepository.saveAndFlush(Date.builder().date(date).build()));
                }
        ).collect(Collectors.toSet());
    }

    public List<java.util.Date> getAllDates(Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        return survey.map(value -> value.getDates()
                .stream().map(com.example.framadate.entity.Date::getDate).collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    public List<java.util.Date> addDates(Long surveyId, Set<java.util.Date> dates) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);

        if (survey.isEmpty()) {
            throw new IllegalArgumentException("survey " + surveyId + " not found");
        }

        var datesFiltrated = filterDates(dates);
        Set<com.example.framadate.entity.Date> dateEntities = this.getOrCreateDates(datesFiltrated);
        survey.get().addDates(dateEntities);
        surveyRepository.saveAndFlush(survey.get());

        return this.toDtos(dateEntities);
    }

    private Set<java.util.Date> filterDates(Set<java.util.Date> dates) {
        return dates.stream().filter(date -> {
            if (date != null)
                return date.compareTo(new java.util.Date()) > 0;
            return false;
        }).collect(Collectors.toSet());
    }

    public Survey deleteDate(Long surveyId, java.util.Date dateId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isEmpty()) {
            throw new IllegalArgumentException("survey " + surveyId + " not found");
        }
        boolean retrieved = survey.get().getDates().removeIf(date -> date.getDate().compareTo(dateId) == 0);
        if (retrieved) {
            surveyRepository.saveAndFlush(survey.get());
            return survey.get();
        }
        throw new IllegalArgumentException("date " + dateId + " not found in survey " + surveyId);
    }

}
