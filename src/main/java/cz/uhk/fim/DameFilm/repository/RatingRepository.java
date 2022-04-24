package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.rating.Rating;
import cz.uhk.fim.DameFilm.entity.rating.RatingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, RatingId> {

    @Query(value = "SELECT avg(star_count) from ratings where movie_id = ?1", nativeQuery = true)
    float getAverageRating(long id);
}
