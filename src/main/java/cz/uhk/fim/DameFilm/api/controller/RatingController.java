package cz.uhk.fim.DameFilm.api.controller;

import cz.uhk.fim.DameFilm.api.url.UrlConstant;
import cz.uhk.fim.DameFilm.dto.in.InRating;
import cz.uhk.fim.DameFilm.dto.out.OutRated;
import cz.uhk.fim.DameFilm.dto.out.OutRating;
import cz.uhk.fim.DameFilm.repository.RatingRepository;
import cz.uhk.fim.DameFilm.service.RatingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = UrlConstant.Rating)
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/rate/{id}")
    public ResponseEntity<OutRating> rate(@RequestBody InRating rating, @PathVariable long id) {
        OutRating rate = ratingService.rate(rating, id);
        return rate.getStarCount() == 0 ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @GetMapping("/rated/{id}")
    public OutRated rated(@PathVariable long id) {
        return ratingService.rated(id);
    }
}
