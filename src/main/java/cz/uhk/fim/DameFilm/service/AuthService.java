package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InUser;
import cz.uhk.fim.DameFilm.dto.in.LoginUser;
import cz.uhk.fim.DameFilm.dto.in.RegisterUser;
import cz.uhk.fim.DameFilm.dto.out.OutUserProfile;
import cz.uhk.fim.DameFilm.dto.out.OutUserLogin;

public interface AuthService {
    OutUserLogin login(LoginUser user);

    OutUserLogin register(RegisterUser user);

    OutUserProfile getUser(long id);

    OutUserProfile deleteUser(long id);

    OutUserProfile updateUser(InUser user, long id);

}
