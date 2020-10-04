package com.example.framadate.service;

import com.example.framadate.entity.Date;
import com.example.framadate.mapper.DateMapper;
import com.example.framadate.repository.DateRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DateService {
    private final DateRepository dateRepository;
    private final DateMapper dateMapper;

    public DateService(DateRepository dateRepository, DateMapper dateMapper) {
        this.dateRepository = dateRepository;
        this.dateMapper = dateMapper;
    }


    public List<java.util.Date> toDtos(Set<Date> dateEntities) {
        return dateMapper.toDtos(dateEntities);
    }

    public Set<Date> getOrCreateDates(Set<java.util.Date> dates) {
        return dates.stream().map(date -> {
           Optional<Date> optDate = dateRepository.findById(date);
            return optDate.orElse(dateRepository.saveAndFlush(Date.builder().date(date).build()));}
        ).collect(Collectors.toSet());
    }

    public Date findbyId(java.util.Date dateId) {
        return dateRepository.findById(dateId).get();
    }
}
