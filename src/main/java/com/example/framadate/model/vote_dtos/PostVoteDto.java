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
public class PostVoteDto {
    @Size(min = 2)
    String comment;
    @NotNull
    Availability availability;
    @PositiveOrZero
    @NotNull
    Long userId;
    @NotNull
    Date dateId;
}
