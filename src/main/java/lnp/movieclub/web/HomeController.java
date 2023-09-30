package lnp.movieclub.web;

import lnp.movieclub.movie.MovieService;
import lnp.movieclub.movie.dto.MovieDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<MovieDto> promotedMovies = movieService.findAllPromotedMovies();
        model.addAttribute("heading","Promowane filmy");
        model.addAttribute("description","Filmy polecane");
        model.addAttribute("movies",promotedMovies);
        return "movie-listing";
    }
}
