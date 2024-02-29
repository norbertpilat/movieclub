package lnp.movieclub.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getCommentsForMovie(Long movieId){
        return commentRepository.findByMovie_Id(movieId);
    }
}
