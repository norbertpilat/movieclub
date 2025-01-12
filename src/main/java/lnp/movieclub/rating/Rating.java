package lnp.movieclub.rating;

import jakarta.persistence.*;
import lnp.movieclub.movie.Movie;
import lnp.movieclub.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_rating")
@NoArgsConstructor
@Data
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
}
