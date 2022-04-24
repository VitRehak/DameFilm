package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InComment;
import cz.uhk.fim.DameFilm.dto.out.OutComment;
import cz.uhk.fim.DameFilm.dto.out.OutUser;

import java.util.List;

public interface CommentService {
    OutComment addComment(InComment comment);

    List<OutComment> getComments();
}
