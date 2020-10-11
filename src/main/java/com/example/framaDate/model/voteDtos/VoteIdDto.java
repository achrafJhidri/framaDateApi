package com.example.framadate.model.voteDtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteIdDto  {
    private Long userId;
    private Date dateId;
    private Long surveyId;
}
