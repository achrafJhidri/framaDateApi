package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "date")
public class Date {
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private java.util.Date date;

    @OneToMany(mappedBy = "date",
//            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Vote> votes;

    @ManyToMany(mappedBy = "dates")
    private Set<Survey> surveys;

    public java.util.Date getDate() {
        return this.date;
    }

    public boolean equals(final Object o) {

        if (!(o instanceof Date)) return false;
        return date.equals(((Date) o).date);
    }

    public int hashCode() {
        return Objects.hashCode(date);
    }

    public String toString() {
        return String.join(",", date.toString());
    }
}
