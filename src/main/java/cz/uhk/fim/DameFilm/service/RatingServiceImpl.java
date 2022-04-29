package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InRating;
import cz.uhk.fim.DameFilm.dto.out.OutRating;
import cz.uhk.fim.DameFilm.entity.rating.Rating;
import cz.uhk.fim.DameFilm.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Optional<Long> getAverageRating(long id) {
        return ratingRepository.getAverageRating(id);
    }

    @Override
    public OutRating rate(InRating rating) {
        Rating appRating = modelMapper.map(rating, Rating.class);
        ratingRepository.save(appRating);
        OutRating outRating = new OutRating();
        outRating.setRated(true);
        return outRating;
    }

}
