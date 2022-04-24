package cz.uhk.fim.DameFilm.dto.in;

import cz.uhk.fim.DameFilm.entity.user.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegisterUser {
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private List<Role> roles;
}
