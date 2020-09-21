package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="date")
public class DateEntity {
    @Id
    private Date date;

    @OneToMany(mappedBy = "date",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<VoteEntity> votes;

    @ManyToMany(mappedBy = "dates")
    private Set<SurveyEntity> surveys;
}
