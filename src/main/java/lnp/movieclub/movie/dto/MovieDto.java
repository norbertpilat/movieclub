package lnp.movieclub.movie.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class MovieDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String originalTitle;
    @Getter
    @Setter
    private Integer releaseYear;
    @Getter
    @Setter
    private String shortDescription;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String youtubeTrailerId;
    @Getter
    @Setter
    private String genre;
    @Getter
    @Setter
    private boolean promoted;
}
