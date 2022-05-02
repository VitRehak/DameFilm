package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InRating;
import cz.uhk.fim.DameFilm.dto.out.OutRated;
import cz.uhk.fim.DameFilm.dto.out.OutRating;

import java.util.Optional;

public interface RatingService {

    Optional<Float> getAverageRating(long id);

    OutRating rate(InRating rating, long id);

    OutRated rated(long id);
}
