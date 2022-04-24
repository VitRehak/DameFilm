package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InUser;
import cz.uhk.fim.DameFilm.dto.in.LoginUser;
import cz.uhk.fim.DameFilm.dto.in.RegisterUser;
import cz.uhk.fim.DameFilm.dto.out.OutUser;

public interface AuthService {
    String login(LoginUser user);

    String register(RegisterUser user);

    OutUser getUser(long id);

    OutUser deleteUser(long id);

    OutUser updateUser(InUser user, long id);

}
