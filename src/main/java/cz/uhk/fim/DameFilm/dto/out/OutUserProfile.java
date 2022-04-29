package cz.uhk.fim.DameFilm.dto.out;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class OutUserProfile {
    private long userId;
    private String email;
    private String username;
    private String lastname;
    private String firstname;
    private ZonedDateTime dateOfRegistration;
}
