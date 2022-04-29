package cz.uhk.fim.DameFilm.entity.rating;

import cz.uhk.fim.DameFilm.entity.movie.Movie;
import cz.uhk.fim.DameFilm.entity.user.User;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class RatingId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
