package com.example.framadate.model.survey_dtos;

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
    @NotNull(message = "leave a limit date")
    @Future(message = "you can't put a limitDate in the past")
    Date limitDate;
}
