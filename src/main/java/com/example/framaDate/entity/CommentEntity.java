package com.example.framaDate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment")
public class CommentEntity {
    @Id
    private Long id;

    private String comment;

    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="sondage_id",nullable = false)
    private SondageEntity sondage;
}
