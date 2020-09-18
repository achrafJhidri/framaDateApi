package com.example.framaDate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vote")
public class VoteEntity {

    @EmbeddedId
    private VoteId voteId;

    private String comment;
    @Enumerated(EnumType.STRING)
    private Disponibilite disponibilite;

}
