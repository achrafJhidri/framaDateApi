package com.example.framaDate.entity; // TODO les noms de packages ne sont pas standards. Suivez les recommendations IntelliJ!

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class UserEntity {
    @Id
    private Long id;

    private String name;
    private String email;

    //TODO Supprimer ces commentaires
 //voteId.user ?? => C'était bien ça
    @OneToMany(mappedBy = "voteId.user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<VoteEntity> votes;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<CommentEntity> comments;

}
