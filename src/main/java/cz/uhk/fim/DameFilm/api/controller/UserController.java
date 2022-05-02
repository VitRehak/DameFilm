package cz.uhk.fim.DameFilm.api.controller;

import cz.uhk.fim.DameFilm.api.url.UrlConstant;
import cz.uhk.fim.DameFilm.dto.in.InUser;
import cz.uhk.fim.DameFilm.dto.in.LoginUser;
import cz.uhk.fim.DameFilm.dto.in.RegisterUser;
import cz.uhk.fim.DameFilm.dto.out.OutUserProfile;
import cz.uhk.fim.DameFilm.dto.out.OutUserLogin;
import cz.uhk.fim.DameFilm.security.JwtTokenProvider;
import cz.uhk.fim.DameFilm.security.UserSecurityService;
import cz.uhk.fim.DameFilm.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public OutUserLogin register(@RequestBody RegisterUser user) {
        log.info("Registration EndPoint");
        return authService.register(user);
    }

    @PostMapping("/login")
    public OutUserLogin login(@RequestBody LoginUser user) {
        log.info("Login EndPoint");
        return authService.login(user);
    }

    @GetMapping("/profile/{id}")
    public OutUserProfile profileData(@PathVariable long id) {
        log.info("Profile Data EndPoint");
        return authService.getUser(id);
    }

    @PutMapping("/profile/update/{id}")
    @PreAuthorize("@permissionEvaluator.canEditUser(principal, #id,)")
    public ResponseEntity<OutUserProfile> update(@RequestBody InUser user, @PathVariable long id) {
        log.info("User Data Update EndPoint");
        return new ResponseEntity<>(authService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/profile/delete/{id}")
    @PreAuthorize("@permissionEvaluator.canEditUser(principal, #id,)")
    public OutUserProfile delete(@PathVariable long id) {
        log.info("Delete User EndPoint");
        return authService.deleteUser(id);
    }
}
