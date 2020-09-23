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
@Table(name="date")
public class Date {
    @Id
    private java.util.Date date;

    @OneToMany(mappedBy = "date",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Vote> votes;

    @ManyToMany(mappedBy = "dates")
    private Set<Survey> surveys;

}
