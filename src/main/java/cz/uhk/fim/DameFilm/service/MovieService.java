package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InMovie;
import cz.uhk.fim.DameFilm.dto.out.OutMovie;
import cz.uhk.fim.DameFilm.entity.user.User;

import java.util.List;

public interface MovieService {

    OutMovie getMovie(long id);

    List<OutMovie> getMovies();

    OutMovie deleteMovie(long id);

    OutMovie updateMovie(InMovie movie, long id);

    OutMovie createMovie(InMovie movie, User user);
}
