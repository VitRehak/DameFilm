package cz.uhk.fim.DameFilm.dto.out;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class OutComment {

    private String text;
    private ZonedDateTime send;
}
