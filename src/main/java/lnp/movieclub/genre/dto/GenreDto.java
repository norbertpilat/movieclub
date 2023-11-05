package lnp.movieclub.genre.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GenreDto {
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
}
