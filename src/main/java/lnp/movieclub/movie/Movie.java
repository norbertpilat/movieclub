package lnp.movieclub.movie;

import jakarta.persistence.*;
import lnp.movieclub.genre.Genre;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String shortDescription;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String youtubeTrailerId;
    @Getter
    @Setter
    private Integer releaseYear;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;
    @Getter
    @Setter
    private boolean promoted;
}
