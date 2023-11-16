package lnp.movieclub.movie;

import lnp.movieclub.movie.dto.MovieDto;
import lnp.movieclub.rating.Rating;

public class MovieDtoMapper {
    static MovieDto mapper(Movie movie){
        double avgRating = movie.getRatings().stream()
                .map(Rating::getRating)
                .mapToDouble(val -> val)
                .average().orElse(0);
        int ratingCount = movie.getRatings().size();
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
                movie.getPoster(),
                avgRating,
                ratingCount
        );
    }
}
