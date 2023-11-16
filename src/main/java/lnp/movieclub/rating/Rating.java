package lnp.movieclub.rating;

import jakarta.persistence.*;
import lnp.movieclub.movie.Movie;
import lnp.movieclub.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie_rating")
@NoArgsConstructor
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private Integer rating;

    public Rating(User user, Movie movie, Integer rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }
}
