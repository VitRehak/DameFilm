package cz.uhk.fim.DameFilm.dto.in;

import cz.uhk.fim.DameFilm.entity.movie.Movie;
import cz.uhk.fim.DameFilm.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
public class InComment {

    private String text;
}
