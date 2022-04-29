package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InUser;
import cz.uhk.fim.DameFilm.dto.in.LoginUser;
import cz.uhk.fim.DameFilm.dto.in.RegisterUser;
import cz.uhk.fim.DameFilm.dto.out.OutUserProfile;
import cz.uhk.fim.DameFilm.dto.out.OutUserToken;

public interface AuthService {
    OutUserToken login(LoginUser user);

    OutUserToken register(RegisterUser user);

    OutUserProfile getUser(long id);

    OutUserProfile deleteUser(long id);

    OutUserProfile updateUser(InUser user, long id);

}
