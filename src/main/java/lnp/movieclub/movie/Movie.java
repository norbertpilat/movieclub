package lnp.movieclub.movie;

import jakarta.persistence.*;
import lnp.movieclub.genre.Genre;
import lnp.movieclub.rating.Rating;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String originalTitle;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;
    private Integer releaseYear;
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;
    @OneToMany(mappedBy = "movie")
    private Set<Rating> ratings = new HashSet<>();
    private boolean promoted;
    private String poster;
}
