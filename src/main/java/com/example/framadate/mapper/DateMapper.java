package com.example.framadate.mapper;

import com.example.framadate.entity.Date;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class DateMapper {


    public List<java.util.Date> toDtos(Set<Date> dateEntities) {
        return dateEntities.stream().map(
                Date::getTheDate)
                .collect(Collectors.toList());

    }
}
