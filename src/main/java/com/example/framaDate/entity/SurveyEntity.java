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
@Table(name="survey")
public class SurveyEntity {

    @Id
    private Long id;

    private String name;
    private String description;
    private Date limitDate;

    @ManyToMany
    @JoinTable(
            name = "Survey_date",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "date_id"))
    private Set<DateEntity> dates;

    @OneToMany(mappedBy = "survey",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<CommentEntity> comments;

    @OneToMany(mappedBy = "survey",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<VoteEntity> votes;

    private boolean closed;

    public boolean isValid(){
        return dates.size()>= 1;
    }
}
