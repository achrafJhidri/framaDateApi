package com.example.framadate.model.voteDtos;

import com.example.framadate.entity.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientVoteDto {
    String comment;
    Availability availability;
    Long userId;
    Date dateId;
}
