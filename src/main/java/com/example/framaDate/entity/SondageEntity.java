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
@Table(name="sondage")
public class SondageEntity {

    @Id
    private Long id;

    private String name;
    private String description;
    private Date limitDate;

    @OneToMany(mappedBy = "sondage",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<DateEntity> dates;

    @OneToMany(mappedBy = "sondage",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<CommentEntity> comments;

    private boolean closed;

    public boolean isValid(){
        return dates.size()>= 2;
    }
}
