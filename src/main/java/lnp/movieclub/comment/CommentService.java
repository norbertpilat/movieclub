package lnp.movieclub.comment;

import lnp.movieclub.comment.dto.CommentDto;
import lnp.movieclub.movie.Movie;
import lnp.movieclub.movie.MovieRepository;
import lnp.movieclub.user.User;
import lnp.movieclub.user.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public List<CommentDto> getCommentsForMovie(Long movieId){
        List<Comment> commentByMovie = commentRepository.findByMovie_Id(movieId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : commentByMovie) {
            User user = comment.getUser();
            CommentDto commentDto = new CommentDto();
            commentDto.setUserEmail(user.getEmail());
            commentDto.setCommentContent(comment.getContent());
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    public boolean hasUserCommentedMovie(Long userId, Long movieId) {
        return commentRepository.existsByUserIdAndMovieId(userId, movieId);
    }

    @Transactional
    public void addComment(long movieId,CommentDto commentDto, String userEmail){
        Comment comment = new Comment();
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        comment.setUser(user);
        comment.setMovie(movie);
        comment.setContent(commentDto.getCommentContent());
        commentRepository.save(comment);
    }
}
