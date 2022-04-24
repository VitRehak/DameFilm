package cz.uhk.fim.DameFilm.dto.in;

import cz.uhk.fim.DameFilm.entity.movie.AgeRating;
import cz.uhk.fim.DameFilm.entity.user.User;
import lombok.Data;

import java.util.List;

@Data
public class InMovie {

    private String name;
    private String director;
    private List<String> actors;
    private AgeRating ageRating;
//    private User publisher;  //nevim asi ne
}
