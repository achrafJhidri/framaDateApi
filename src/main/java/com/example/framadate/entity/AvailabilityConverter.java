package com.example.framadate.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AvailabilityConverter implements AttributeConverter<Availability, String> {

    @Override
    public String convertToDatabaseColumn(Availability category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public Availability convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Availability.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}