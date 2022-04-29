package cz.uhk.fim.DameFilm.dto.out;

import cz.uhk.fim.DameFilm.entity.user.Role;
import lombok.Data;

import java.util.List;

@Data
public class OutUserToken {
    private String token;
    private List<Role> roles;
}
