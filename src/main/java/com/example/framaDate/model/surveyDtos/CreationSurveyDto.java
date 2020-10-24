package com.example.framadate.model.surveyDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreationSurveyDto {
    @NotBlank(message = "leave a valid name")
    @Size(min = 2)
    String name;
    @NotBlank(message = "leave a valid description")
    @Size(min = 5)
    String description;
    @NotNull
    @Future(message = "you can't pu a limitDate in the past")
    Date limitDate;
}
