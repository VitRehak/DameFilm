package cz.uhk.fim.DameFilm.dto.out;

import cz.uhk.fim.DameFilm.entity.user.Role;
import lombok.Data;

import java.util.List;

@Data
public class OutUserLogin {
    private String token;
    private List<Role> roles;
    private long userId;
}
