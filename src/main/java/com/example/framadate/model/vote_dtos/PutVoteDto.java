package com.example.framadate.model.vote_dtos;

import com.example.framadate.entity.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutVoteDto {
    @Size(message = "the comment must have at least 2 characters", min = 2)
    String comment;
    Availability availability;
    @PositiveOrZero
    @NotNull(message = "userId mustn't be null")
    Long userId;
    @NotNull(message = "dateId mustn't be null")
    Date dateId;
}
