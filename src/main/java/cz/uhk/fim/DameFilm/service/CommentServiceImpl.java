package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InComment;
import cz.uhk.fim.DameFilm.dto.out.OutComment;
import cz.uhk.fim.DameFilm.dto.out.OutMovie;
import cz.uhk.fim.DameFilm.entity.comment.Comment;
import cz.uhk.fim.DameFilm.entity.movie.Movie;
import cz.uhk.fim.DameFilm.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Clock clock;

    @Override
    public OutComment addComment(InComment comment) {
        Comment appComment = modelMapper.map(comment, Comment.class);
        appComment.setSend(ZonedDateTime.now(clock));
        commentRepository.save(appComment);
        return modelMapper.map(appComment,OutComment.class);
    }

    @Override
    public List<OutComment> getComments() {
        List<Comment> comments = (List<Comment>) commentRepository.findAll();
        return comments.stream().map(c -> modelMapper.map(c, OutComment.class)).collect(Collectors.toList());
    }
}
