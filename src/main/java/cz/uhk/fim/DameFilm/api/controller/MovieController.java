package cz.uhk.fim.DameFilm.api.controller;

import cz.uhk.fim.DameFilm.api.url.UrlConstant;
import cz.uhk.fim.DameFilm.dto.in.InMovie;
import cz.uhk.fim.DameFilm.dto.out.OutMovie;
import cz.uhk.fim.DameFilm.entity.user.User;
import cz.uhk.fim.DameFilm.security.JwtUserDetails;
import cz.uhk.fim.DameFilm.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
        User user = ((JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        return movieService.createMovie(movie, user);
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
    @PreAuthorize("@permissionEvaluator.canEditMovie(principal, #id,)")
    public OutMovie update(@RequestBody InMovie movie, @PathVariable long id) {
        log.info("Movie Update EndPoint");
        return movieService.updateMovie(movie, id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@permissionEvaluator.canEditMovie(principal, #id,)")
    public OutMovie delete(@PathVariable long id) {
        log.info("Delete Movie EndPoint");
        return movieService.deleteMovie(id);
    }
}
