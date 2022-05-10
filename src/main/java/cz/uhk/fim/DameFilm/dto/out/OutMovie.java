package cz.uhk.fim.DameFilm.dto.out;

import cz.uhk.fim.DameFilm.entity.movie.AgeRating;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OutMovie {
    private long movieId;
    private String name;
    private String director;
    private String create;
    private String lastUpdate;
    private List<String> actors;
    private AgeRating ageRating;
    private OutUserProfile publisher;
    private float averageRating;
}
