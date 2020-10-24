package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String comment;
    private Date creationDate;
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="survey_id",nullable = false)
    private Survey survey;
    public boolean equals(final Object o) {
        if (!(o instanceof Comment)) return false;
        return id.equals(((Comment) o).id);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String toString() {
        return String.join(",",comment,creationDate.toString(),lastUpdate.toString(),user.toString());
    }
    public Long getUserId(){
        return  user.getId();
    }
}
