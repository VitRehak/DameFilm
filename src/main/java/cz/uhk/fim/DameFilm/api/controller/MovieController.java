package cz.uhk.fim.DameFilm.api.controller;

import cz.uhk.fim.DameFilm.api.url.UrlConstant;
import cz.uhk.fim.DameFilm.dto.in.InMovie;
import cz.uhk.fim.DameFilm.dto.out.OutMovie;
import cz.uhk.fim.DameFilm.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(UrlConstant.MOVIE)
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/create")
    public OutMovie addMovie(@RequestBody InMovie movie) {
        log.info("Create movie EndPoint");
        return movieService.createMovie(movie);
    }

    @GetMapping("/all")
    public List<OutMovie> getMovies() {
        log.info("Get All Movies EndPoint");
        return movieService.getMovies();
    }

    @GetMapping("/detail/{id}")
    public OutMovie getMovie(@PathVariable long id) {
        log.info("Get Movie EndPoint");
        return movieService.getMovie(id);
    }

    @PutMapping("/update/{id}")
    public OutMovie update(@RequestBody InMovie movie, @PathVariable long id) {
        log.info("Movie Update EndPoint");
        return movieService.updateMovie(movie, id);
    }

    @DeleteMapping("/delete/{id}")
    public OutMovie delete(@PathVariable long id) {
        log.info("Delete Movie EndPoint");
        return movieService.deleteMovie(id);
    }
}
