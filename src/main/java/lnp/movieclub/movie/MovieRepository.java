package lnp.movieclub.movie;

import lnp.movieclub.genre.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie,Long> {
    List<Movie> findAllByPromotedIsTrue();
    List<Movie> findAllByGenre_NameIgnoreCase(String genre);
    List<Movie> findAllByGenreId(Long id);
    @Query("select m from Movie m join m.ratings r group by m order by avg(r.rating) desc")
    List<Movie> findTopByRating(Pageable page);
    Optional<Movie> findByTitleIgnoreCase(String title);
}
