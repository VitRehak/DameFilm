package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InRating;
import cz.uhk.fim.DameFilm.dto.out.OutRated;
import cz.uhk.fim.DameFilm.dto.out.OutRating;
import cz.uhk.fim.DameFilm.entity.movie.Movie;
import cz.uhk.fim.DameFilm.entity.rating.Rating;
import cz.uhk.fim.DameFilm.entity.user.User;
import cz.uhk.fim.DameFilm.repository.RatingRepository;
import cz.uhk.fim.DameFilm.security.JwtUserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    MovieService movieService;

    @Override
    public Optional<Float> getAverageRating(long id) {
        return ratingRepository.getAverageRating(id);
    }

    @Override
    public OutRating rate(InRating inRating, long id) {
        User user = ((JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Movie appMovie = movieService.getAppMovie(id);
        Optional<Rating> rated = ratingRepository.isRated(appMovie.getMovieId(), user.getUserId());
        OutRating outRating = new OutRating();
        if (rated.isPresent()) {
            log.error("User Already Rated This Movie");
            outRating.setStarCount(0);
            return outRating;
        }
        Rating rating = new Rating();
        rating.setLiker(user);
        rating.setStarCount(inRating.getStarCount());
        rating.setMovie(appMovie);
        ratingRepository.save(rating);

        outRating.setStarCount(rating.getStarCount());
        return outRating;
    }

    @Override
    public OutRated rated(long id) {
        long userId = ((JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserId();
        long movieId = movieService.getAppMovie(id).getMovieId();
        OutRated outRated = new OutRated();
        Optional<Rating> rated = ratingRepository.isRated(movieId, userId);
        outRated.setRated(rated.isPresent());
        return outRated;
    }
}
