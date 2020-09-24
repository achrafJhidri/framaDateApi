package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vote")
public class Vote {

    @EmbeddedId
    VoteId voteId;

    String comment;

    Availability availability;

    java.util.Date votingDate;

    java.util.Date lastUpdate;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("dateId")
    @JoinColumn(name = "date_id")
    Date date;

    @ManyToOne
    @MapsId("surveyId")
    @JoinColumn(name = "survey_id")
    Survey survey;




}
