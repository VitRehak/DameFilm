package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(value = "SELECT avg(star_count) FROM ratings WHERE movie_id = ?1", nativeQuery = true)
    Optional<Float> getAverageRating(long id);

    @Query(value = "SELECT * FROM ratings WHERE movie_id = ?1 AND user_id = ?2", nativeQuery = true)
    Optional<Rating> isRated(long movieId, long userId);

}
