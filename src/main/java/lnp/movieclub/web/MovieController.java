package lnp.movieclub.web;

import lnp.movieclub.comment.Comment;
import lnp.movieclub.comment.CommentService;
import lnp.movieclub.movie.MovieService;
import lnp.movieclub.movie.dto.MovieDto;
import lnp.movieclub.rating.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final RatingService ratingService;
    private final CommentService commentService;

    @GetMapping("/film/{id}")
    public String getMovie(@PathVariable long id, Model model, Authentication authentication){
        MovieDto movie = movieService.findMovieById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Comment> comments = commentService.getCommentsForMovie(id);
        model.addAttribute("comments",comments);
        model.addAttribute("username",authentication.getName());
        model.addAttribute("movie",movie);
        //jeżeli user jest zalogowany
        if (authentication != null){
            String currentUserEmail = authentication.getName();
            //wyszukanie jego głosu
            Integer rating = ratingService.getUserRatingForMovie(currentUserEmail, id).orElse(0);
            //i zapisujemy go w modelu
            model.addAttribute("userRating",rating);
        }
        return "movie";
    }

    @GetMapping("/top10")
    public String findTop10(Model model){
        List<MovieDto> top10Movies = movieService.findTopMovies(10);
        model.addAttribute("heading","Filmowe TOP10");
        model.addAttribute("description","Filmy najlepiej oceniane przez użytkowników");
        model.addAttribute("movies",top10Movies);
        return "movie-listing";
    }
}
