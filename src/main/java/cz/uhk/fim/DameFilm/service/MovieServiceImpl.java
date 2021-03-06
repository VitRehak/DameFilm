package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InMovie;
import cz.uhk.fim.DameFilm.dto.out.OutMovie;
import cz.uhk.fim.DameFilm.entity.movie.Movie;
import cz.uhk.fim.DameFilm.entity.rating.Rating;
import cz.uhk.fim.DameFilm.entity.user.User;
import cz.uhk.fim.DameFilm.repository.MovieRepository;
import cz.uhk.fim.DameFilm.repository.RatingRepository;
import cz.uhk.fim.DameFilm.security.JwtUserDetails;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Clock clock;
    @Autowired
    DateTimeFormatter customDateTimeFormatter;


    @Override
    public OutMovie getMovie(long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            log.error("Movie Not Found");
            return null;
        }
        OutMovie outMovie = modelMapper.map(movie.get(), OutMovie.class);
        Optional<Float> averageRating = ratingRepository.getAverageRating(id);
        if (averageRating.isEmpty()) {
            outMovie.setAverageRating(0);
        } else {
            outMovie.setAverageRating(averageRating.get());
        }
        outMovie.setCreate(movie.get().getCreate().format(customDateTimeFormatter));
        outMovie.setLastUpdate(movie.get().getLastUpdate().format(customDateTimeFormatter));
        log.error("Movie Loaded");
        return outMovie;
    }

    @Override
    public Movie getAppMovie(long id) {
        Optional<Movie> OMovie = movieRepository.findById(id);
        if (OMovie.isEmpty()) {
            log.error("Movie Not Found");
            return null;
        }
        return OMovie.get();
    }

    @Override
    public List<OutMovie> getMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<OutMovie> outMovies = movies.stream()
                .map(m -> modelMapper.map(m, OutMovie.class)).collect(Collectors.toList());
        outMovies.forEach(m -> {
            Optional<Float> averageRating = ratingRepository.getAverageRating(m.getMovieId());
            if (averageRating.isEmpty()) {
                m.setAverageRating(0);
            } else {
                m.setAverageRating(averageRating.get());
            }
        });
        log.error("Movies Loaded");
        return outMovies;
    }

    @Override
    public OutMovie deleteMovie(long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            log.error("Movie Not Found");
            return null;
        }
        movieRepository.deleteById(id);
        OutMovie outMovie = modelMapper.map(movie.get(), OutMovie.class);
        Optional<Float> averageRating = ratingRepository.getAverageRating(id);
        if (averageRating.isEmpty()) {
            outMovie.setAverageRating(0);
        } else {
            outMovie.setAverageRating(averageRating.get());
        }
        log.error("Movie Deleted");
        return outMovie;
    }

    @Override
    public OutMovie updateMovie(InMovie movie, long id) {
        Optional<Movie> OMovie = movieRepository.findById(id);
        if (OMovie.isEmpty()) {
            log.error("Movie Not Found");
            return null;
        }

        Movie appMovie = modelMapper.map(movie, Movie.class);
        Movie dbMovie = OMovie.get();
        dbMovie.setActors(appMovie.getActors());
        dbMovie.setLastUpdate(ZonedDateTime.now(clock));
        dbMovie.setDirector(appMovie.getDirector());
        dbMovie.setName(appMovie.getName());
        dbMovie.setPublisher(appMovie.getPublisher());
        dbMovie.setAgeRating(appMovie.getAgeRating());

        movieRepository.save(dbMovie);
        OutMovie outMovie = modelMapper.map(dbMovie, OutMovie.class);
        Optional<Float> averageRating = ratingRepository.getAverageRating(dbMovie.getMovieId());
        if (averageRating.isEmpty()) {
            outMovie.setAverageRating(0);
        } else {
            outMovie.setAverageRating(averageRating.get());
        }
        log.error("Movie Updated");
        return outMovie;
    }

    @Override
    public OutMovie createMovie(InMovie movie, User user) {
        Movie appMovie = new Movie();
        appMovie.setName(movie.getName());
        appMovie.setDirector(movie.getDirector());
        appMovie.setAgeRating(movie.getAgeRating());
        appMovie.setActors(movie.getActors());
        appMovie.setLastUpdate(ZonedDateTime.now(clock));
        appMovie.setCreate(ZonedDateTime.now(clock));
        appMovie.setPublisher(user);
        movieRepository.save(appMovie);
        OutMovie outMovie = modelMapper.map(appMovie, OutMovie.class);
        outMovie.setAverageRating(0);
        log.error("Movie Created");
        return outMovie;
    }
}

