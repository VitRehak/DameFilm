package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.comment.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
