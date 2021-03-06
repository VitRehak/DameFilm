package cz.uhk.fim.DameFilm.entity.movie;

import cz.uhk.fim.DameFilm.entity.comment.Comment;
import cz.uhk.fim.DameFilm.entity.rating.Rating;
import cz.uhk.fim.DameFilm.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity(name = "movies")
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id" )
    private long movieId;

    @Column(nullable = false)
    private String name;

    private String director;
    private ZonedDateTime create;
    private ZonedDateTime lastUpdate;

    @ElementCollection
    private List<String> actors;

    @Enumerated(value = EnumType.STRING)
    private AgeRating ageRating;

    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private User publisher;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Comment> comments;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Rating> ratings;
}

