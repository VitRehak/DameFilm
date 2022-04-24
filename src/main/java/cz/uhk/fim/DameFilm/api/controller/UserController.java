package cz.uhk.fim.DameFilm.api.controller;

import cz.uhk.fim.DameFilm.api.url.UrlConstant;
import cz.uhk.fim.DameFilm.dto.in.InUser;
import cz.uhk.fim.DameFilm.dto.in.LoginUser;
import cz.uhk.fim.DameFilm.dto.in.RegisterUser;
import cz.uhk.fim.DameFilm.dto.out.OutUser;
import cz.uhk.fim.DameFilm.security.JwtTokenProvider;
import cz.uhk.fim.DameFilm.security.JwtUserDetails;
import cz.uhk.fim.DameFilm.security.UserSecurityService;
import cz.uhk.fim.DameFilm.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping(UrlConstant.AUTH)
public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthService authService;

    @Autowired
    UserSecurityService userSecurityService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterUser user) {
        log.info("Registration EndPoint");
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUser user) {
        log.info("Login EndPoint");
        return authService.login(user);
    }

    @GetMapping("/profile/{id}")
    public OutUser profileData(@PathVariable long id) {
        log.info("Profile Data EndPoint");
        return authService.getUser(id);
    }

    @PutMapping("/profile/update/{id}")
    public ResponseEntity<OutUser> update(@RequestBody InUser user, @PathVariable long id) {
        log.info("User Data Update EndPoint");
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        long userId = userSecurityService.loadIdByEmail(userDetails.getEmail());
        if (userId == id || roles.contains("ROLE_ADMIN")) {
            return new ResponseEntity<>(authService.updateUser(user, id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/profile/delete/{id}")
    public OutUser delete(@PathVariable long id) {
        log.info("Delete User EndPoint");
        return authService.deleteUser(id);
    }
}
