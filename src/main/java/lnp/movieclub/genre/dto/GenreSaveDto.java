package lnp.movieclub.genre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenreSaveDto {
    private String name;
    private String description;
}
