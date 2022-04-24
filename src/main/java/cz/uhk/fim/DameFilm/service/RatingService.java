package cz.uhk.fim.DameFilm.service;

import antlr.collections.impl.IntRange;
import cz.uhk.fim.DameFilm.dto.in.InRating;
import cz.uhk.fim.DameFilm.dto.out.OutRating;
import cz.uhk.fim.DameFilm.entity.rating.Rating;

import java.util.List;

public interface RatingService {

    float getAverageRating(long id);

    OutRating rate(InRating rating);
}
