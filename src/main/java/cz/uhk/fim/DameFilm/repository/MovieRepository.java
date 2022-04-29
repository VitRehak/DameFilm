package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query(value = "DELETE FROM movies WHERE publisher_id = ?1", nativeQuery = true)
    void deleteByPublisher(long id);

    @Query(value = "SELECT * FROM movies WHERE publisher_id = ?1", nativeQuery = true)
    List<Movie> findByPublisher(long id);
}
