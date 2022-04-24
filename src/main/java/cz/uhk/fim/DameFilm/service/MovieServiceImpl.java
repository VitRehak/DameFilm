package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InMovie;
import cz.uhk.fim.DameFilm.dto.out.OutMovie;
import cz.uhk.fim.DameFilm.entity.movie.Movie;
import cz.uhk.fim.DameFilm.repository.MovieRepository;
import cz.uhk.fim.DameFilm.repository.RatingRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    RatingService ratingService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Clock clock;


    @Override
    public OutMovie getMovie(long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            log.error("Movie Not Found");
            return null;
        }
        OutMovie outMovie = modelMapper.map(movie.get(), OutMovie.class);
        outMovie.setAverageRating(ratingService.getAverageRating(id));
        log.error("Movie Loaded");
        return outMovie;
    }

    @Override
    public List<OutMovie> getMovies() {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        List<OutMovie> outMovies = movies.stream()
                .map(m -> modelMapper.map(m, OutMovie.class)).collect(Collectors.toList());
        outMovies.forEach(m -> m.setAverageRating(ratingService.getAverageRating(m.getMovieId())));
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
        outMovie.setAverageRating(ratingService.getAverageRating(id));
        log.error("Movie Deleted");
        return outMovie;
    }

    @Override
    public OutMovie updateMovie(InMovie movie,long id) {//todo nacist upravit zapsat
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
        outMovie.setAverageRating(ratingService.getAverageRating(dbMovie.getMovieId()));
        log.error("Movie Updated");
        return outMovie;
    }

    @Override
    public OutMovie createMovie(InMovie movie) {
        Movie appMovie = modelMapper.map(movie, Movie.class);
        appMovie.setLastUpdate(ZonedDateTime.now(clock));
        appMovie.setCreate(ZonedDateTime.now(clock));
        movieRepository.save(appMovie);
        OutMovie outMovie = modelMapper.map(appMovie, OutMovie.class);
        outMovie.setAverageRating(ratingService.getAverageRating(appMovie.getMovieId()));
        log.error("Movie Created");
        return outMovie;
    }
}

