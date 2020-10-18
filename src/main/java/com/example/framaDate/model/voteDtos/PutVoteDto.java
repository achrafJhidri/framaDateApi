package com.example.framadate.model.voteDtos;

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
    @Size(min=2)
    String comment;
    Availability availability;
    @PositiveOrZero
    @NotNull
    Long userId;
    @NotNull
    Date dateId;
}
