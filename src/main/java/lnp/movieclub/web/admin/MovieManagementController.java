package lnp.movieclub.web.admin;

import lnp.movieclub.genre.GenreService;
import lnp.movieclub.genre.dto.GenreDto;
import lnp.movieclub.movie.MovieService;
import lnp.movieclub.movie.dto.MovieSaveDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovieManagementController {
    private final MovieService movieService;
    private final GenreService genreService;

    public MovieManagementController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping("/admin/dodaj-film")
    public String addMovie(Model model){
        List<GenreDto> allGenres = genreService.findAllGenres();
        model.addAttribute("genres", allGenres);
        MovieSaveDto movieSaveDto = new MovieSaveDto();
        model.addAttribute("movie",movieSaveDto);
        return "admin/movie-form";
    }

    @PostMapping("admin/dodaj-film")
    public String addMovie(MovieSaveDto movie, RedirectAttributes redirectAttributes){
        movieService.addMovie(movie);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został zapisany".formatted(movie.getTitle())
        );
        return "redirect:/admin";
    }
}
