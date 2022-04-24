package cz.uhk.fim.DameFilm.dto.in;

import lombok.Data;

@Data
public class LoginUser {
    private String email;
    private String password;
}
