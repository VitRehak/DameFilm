package cz.uhk.fim.DameFilm.dto.out;

import cz.uhk.fim.DameFilm.entity.user.Role;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OutUser {
    private long userId;
    private String email;
    private String username;
    private String lastname;
    private String firstname;
    private ZonedDateTime dateOfRegistration;
}
