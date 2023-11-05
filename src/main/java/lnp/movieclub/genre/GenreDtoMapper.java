package lnp.movieclub.genre;

import lnp.movieclub.genre.dto.GenreDto;

public class GenreDtoMapper {
    static GenreDto map(Genre genre){
        return new GenreDto(
                genre.getId(),
                genre.getName(),
                genre.getDescription()
        );
    }
}
