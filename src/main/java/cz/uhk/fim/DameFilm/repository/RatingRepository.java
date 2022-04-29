package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.rating.Rating;
import cz.uhk.fim.DameFilm.entity.rating.RatingId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    @Query("DELETE FROM ratings WHERE movie_id = ?1")
    void deleteByMovieId(long id);

    @Query(value = "SELECT avg(star_count) FROM ratings WHERE movie_id = ?1", nativeQuery = true)
    Optional<Long> getAverageRating(long id);
}
