package com.example.framadate.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteIdDto implements Serializable {
    private Long userId;
    private Date dateId;
    private Long surveyId;
}
