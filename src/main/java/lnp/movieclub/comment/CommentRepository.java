package lnp.movieclub.comment;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByMovie_Id(Long movieId);

    boolean existsByUserIdAndMovieId(Long userId, Long movieId);
}
