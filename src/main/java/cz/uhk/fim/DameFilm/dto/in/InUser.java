package cz.uhk.fim.DameFilm.dto.in;

import lombok.Data;

@Data
public class InUser {
    private String password;
    private String username;
    private String lastname;
    private String firstname;
}
