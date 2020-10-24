package com.example.framadate.model.surveyDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutSurveyDto {
    @Size(min = 2)
    String name;
    @Size(min = 5)
    String description;
    @Future(message = "you can't put a limitDate in the past")
    Date limitDate;

    Boolean close;
}
