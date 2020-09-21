package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VoteId implements Serializable {

    @Column(name="user_id")
    private Long userId;

    @Column(name="date_id")
    private Date dateId;

    @Column(name="survey_id")
    private Long survey;

}
