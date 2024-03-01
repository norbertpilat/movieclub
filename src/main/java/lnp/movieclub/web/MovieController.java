package lnp.movieclub.web;

import lnp.movieclub.comment.Comment;
import lnp.movieclub.comment.CommentService;
import lnp.movieclub.comment.dto.CommentDto;
import lnp.movieclub.movie.MovieService;
import lnp.movieclub.movie.dto.MovieDto;
import lnp.movieclub.rating.RatingService;
import lnp.movieclub.user.User;
import lnp.movieclub.user.UserService;
import lnp.movieclub.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class MovieController {
    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    private final MovieService movieService;
    private final RatingService ratingService;
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/film/{id}")
    public String getMovie(@PathVariable long id, Model model, Authentication authentication){
        MovieDto movie = movieService.findMovieById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<CommentDto> comments = commentService.getCommentsForMovie(id);
        CommentDto addComment = new CommentDto();
        model.addAttribute("movie",movie);
        //jeżeli user jest zalogowany
        if (authentication != null){
            String currentUserEmail = authentication.getName();
            addComment.setUserEmail(authentication.getName());
            //wyszukanie jego głosu
            Integer rating = ratingService.getUserRatingForMovie(currentUserEmail, id).orElse(0);
            //i zapisujemy go w modelu
            model.addAttribute("userRating",rating);
        }
        model.addAttribute("comments",comments);
        model.addAttribute("addComment",addComment);
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

    @PostMapping("/film/{id}")
    public String addComment(@PathVariable long id, CommentDto commentDto ,RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        UserDto userDto = userService.findUserByEmail(currentUserEmail)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
        MovieDto movieDto = movieService.findMovieById(id).orElseThrow();
        if (commentService.hasUserCommentedMovie(userDto.getId(), id)) {
            redirectAttributes.addFlashAttribute(
                    NOTIFICATION_ATTRIBUTE,
                    "Dodałeś już komentarz do filmu %s:".formatted(movieDto.getTitle())
            );
            return "redirect:/film/{id}";
        }
        commentService.addComment(id,commentDto,currentUserEmail);
        return "redirect:/film/{id}";
    }
}
