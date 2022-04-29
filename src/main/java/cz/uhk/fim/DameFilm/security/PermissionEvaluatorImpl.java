package cz.uhk.fim.DameFilm.security;


import cz.uhk.fim.DameFilm.dto.out.OutMovie;
import cz.uhk.fim.DameFilm.entity.user.Role;
import cz.uhk.fim.DameFilm.entity.user.User;
import cz.uhk.fim.DameFilm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "permissionEvaluator")
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    @Autowired
    MovieService movieService;

    @Override
    public boolean canEditUser(Object principal, long requestId) {
        JwtUserDetails userDetails = ((JwtUserDetails) principal);
        long userId = userDetails.getUser().getUserId();
        boolean admin = userDetails.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"));
        boolean owner = userId == requestId;
        return admin || owner;
    }

    @Override
    public boolean canEditMovie(Object principal, long requestId) {
        long publisherId = movieService.getMovie(requestId).getPublisher().getUserId();
        JwtUserDetails userDetails = ((JwtUserDetails) principal);
        long userId = userDetails.getUser().getUserId();
        boolean admin = userDetails.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"));
        boolean owner = userId == publisherId;
        return admin || owner;
    }
}
