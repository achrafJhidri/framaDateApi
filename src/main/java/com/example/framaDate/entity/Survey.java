package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="survey")
public class Survey {


    @Id
    private Long id;

    private String name;

    private String description;

    private java.util.Date limitDate;

    @ManyToMany
    @JoinTable(
            name = "Survey_date",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "date_id"))
    private Set<Date> dates;

    @OneToMany(mappedBy = "survey",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "survey",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Vote> votes;

    private Boolean closed;

}
