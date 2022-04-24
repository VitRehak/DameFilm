package cz.uhk.fim.DameFilm.dto.out;

import cz.uhk.fim.DameFilm.entity.movie.AgeRating;
import cz.uhk.fim.DameFilm.entity.user.User;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OutMovie {
    private long movieId;
    private String name;
    private String director;
    private ZonedDateTime create;
    private ZonedDateTime lastUpdate;
    private List<String> actors;
    private AgeRating ageRating;
    private User publisher;
    private float averageRating;
}
