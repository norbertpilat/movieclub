package lnp.movieclub.movie;

import lnp.movieclub.movie.dto.MovieDto;

public class MovieDtoMapper {
    static MovieDto mapper(Movie movie){
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getOriginalTitle(),
                movie.getReleaseYear(),
                movie.getShortDescription(),
                movie.getDescription(),
                movie.getYoutubeTrailerId(),
                movie.getGenre().getName(),
                movie.isPromoted(),
                movie.getPoster()
        );
    }
}
