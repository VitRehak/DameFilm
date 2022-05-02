package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT * FROM movies WHERE movie_id = ?1", nativeQuery = true)
    Optional<Movie> findAppMovie(long id);
}
