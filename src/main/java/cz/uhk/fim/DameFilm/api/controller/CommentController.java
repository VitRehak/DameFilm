package cz.uhk.fim.DameFilm.api.controller;

import cz.uhk.fim.DameFilm.api.url.UrlConstant;
import cz.uhk.fim.DameFilm.dto.in.InComment;
import cz.uhk.fim.DameFilm.dto.out.OutComment;
import cz.uhk.fim.DameFilm.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(UrlConstant.Comment)
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/create")
    public OutComment createComment(@RequestBody InComment comment){
        log.info("Create Comment EndPoint");
        return commentService.addComment(comment);
    }

    @GetMapping("/{id}/all")
    public List<OutComment> getAll(@PathVariable long id){
       log.info("All Comments EndPoint");
        return commentService.getComments(id);
    }

}
