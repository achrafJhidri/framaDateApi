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
    private Long id;

    private Date date;

    @OneToMany(mappedBy = "voteId.date",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<VoteEntity> votes;

    @ManyToOne
    @JoinColumn(name="sondage_id",nullable = false)
    private SondageEntity sondage;
}
