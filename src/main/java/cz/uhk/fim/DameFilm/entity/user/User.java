package cz.uhk.fim.DameFilm.entity.user;

import cz.uhk.fim.DameFilm.entity.comment.Comment;
import cz.uhk.fim.DameFilm.entity.rating.Rating;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id" )
    private long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private List<Role> roles;

    private String lastname;
    private String firstname;
    private ZonedDateTime dateOfRegistration;
}
