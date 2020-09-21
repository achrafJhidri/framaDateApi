package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment")
public class CommentEntity {
    @Id
    private Long id;

    private String comment;

    private Date creationDate;

    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="survey_id",nullable = false)
    private SurveyEntity survey;
}
