package cz.uhk.fim.DameFilm.entity.rating;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ratings")
@Table(name = "ratings")
public class Rating {

    @EmbeddedId
    private RatingId ratingId;

    private double starCount;
}
