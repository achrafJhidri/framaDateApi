package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VoteId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "date_id")
    private Date dateId;
    @Column(name = "survey_id")
    private Long surveyId;

    public boolean equals(final Object o) {
        if (!(o instanceof VoteId)) return false;
        final VoteId other = (VoteId) o;
        return dateId.equals(other.dateId) && userId.equals(other.userId) && surveyId.equals(other.surveyId);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Long user = this.getUserId();
        result = result * PRIME + (user == null ? 43 : user.hashCode());
        final Date date = this.getDateId();
        result = result * PRIME + (date == null ? 43 : date.hashCode());
        final Long survey = this.getSurveyId();
        result = result * PRIME + (survey == null ? 43 : survey.hashCode());
        return result;
    }

    public String toString() {
        return "VoteId(userId=" + this.getUserId() + ", dateId=" + this.getDateId() + ", surveyId=" + this.getSurveyId() + ")";
    }
}
