package cz.uhk.fim.DameFilm.dto.in;

import cz.uhk.fim.DameFilm.entity.movie.Movie;
import cz.uhk.fim.DameFilm.entity.user.User;
import lombok.Data;

@Data
public class InRating {

    private int starCount;
}
