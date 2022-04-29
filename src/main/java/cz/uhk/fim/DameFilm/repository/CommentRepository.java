package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "SELECT * from comments where movie_id = ?1", nativeQuery = true)
    List<Comment> findAllFromMovie(long id);

}
