package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "survey")
public class Survey {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private java.util.Date limitDate;

    @ManyToMany
    @JoinTable(
            name = "Survey_date",
            joinColumns = @JoinColumn(name = "survey_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "date_id", nullable = false, updatable = false))
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


    public void addDates(Set<Date> dates) {
        this.dates.addAll(dates);
    }

    public boolean equals(final Object o) {
        if (!(o instanceof Survey)) return false;
        return id.equals(((Survey) o).id);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String toString() {
        return String.join(",", name, description, limitDate.toString());
    }


}
