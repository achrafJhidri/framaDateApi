package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
@Builder
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




    public boolean equals(final Object o) {
        if (!(o instanceof Vote)) return false;
        return voteId.equals(((Vote) o).voteId);
    }
    public int hashCode() {
        return Objects.hashCode(voteId);
    }

    public String toString() {
        return String.join(",",voteId.toString(),availability.toString(),comment,votingDate.toString(),lastUpdate.toString());
    }
}
