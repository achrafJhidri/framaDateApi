package com.example.framadate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Vote> votes;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public boolean equals(final Object o) {
        if (!(o instanceof User)) return false;
        return id.equals(((User) o).id );
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String toString() {
        return String.join(",", id.toString(),name,email);
    }
}
