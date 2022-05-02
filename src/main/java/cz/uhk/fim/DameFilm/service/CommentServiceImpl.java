package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InComment;
import cz.uhk.fim.DameFilm.dto.out.OutComment;
import cz.uhk.fim.DameFilm.dto.out.OutMovie;
import cz.uhk.fim.DameFilm.entity.comment.Comment;
import cz.uhk.fim.DameFilm.entity.movie.Movie;
import cz.uhk.fim.DameFilm.entity.user.User;
import cz.uhk.fim.DameFilm.repository.CommentRepository;
import cz.uhk.fim.DameFilm.repository.MovieRepository;
import cz.uhk.fim.DameFilm.security.JwtUserDetails;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Clock clock;


    @Override
    public OutComment addComment(InComment comment, long id) {
        User user = ((JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Optional<Movie> OMovie = movieRepository.findAppMovie(id);
        if (OMovie.isEmpty()) {
            log.error("Movie Not Found");
            return null;
        }
        Movie movie = OMovie.get();
        Comment appComment = modelMapper.map(comment, Comment.class);
        appComment.setSend(ZonedDateTime.now(clock));
        appComment.setAuthor(user);
        appComment.setMovie(movie);
        commentRepository.save(appComment);
        return modelMapper.map(appComment, OutComment.class);
    }

    @Override
    public List<OutComment> getComments(long id) {
        List<Comment> comments = commentRepository.findAllFromMovie(id);
        return comments.stream().map(c -> modelMapper.map(c, OutComment.class)).collect(Collectors.toList());
    }
}
