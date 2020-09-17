package com.example.framaDate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VoteId implements Serializable {
//    @ManyToOne
//    @JoinColumn(name="user_id",nullable = false)
    private UserEntity user;

//    @ManyToOne
//    @JoinColumn(name="date_id",nullable = false)
    private DateEntity date;


}