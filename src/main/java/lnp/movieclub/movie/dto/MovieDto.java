package lnp.movieclub.movie.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private String originalTitle;
    private Integer releaseYear;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;
    private String genre;
    private boolean promoted;
    private String poster;
    private double avgRating;
    private int ratingCount;
}
