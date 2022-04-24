package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.movie.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Long> {
}
