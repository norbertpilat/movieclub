package lnp.movieclub.web.admin;

import lnp.movieclub.genre.GenreService;
import lnp.movieclub.genre.dto.GenreDto;
import lnp.movieclub.movie.MovieService;
import lnp.movieclub.movie.dto.MovieDto;
import lnp.movieclub.movie.dto.MovieSaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
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
    public String addMovie(Model model) {
        List<GenreDto> allGenres = genreService.findAllGenres();
        model.addAttribute("genres", allGenres);
        MovieSaveDto movieSaveDto = new MovieSaveDto();
        model.addAttribute("movie", movieSaveDto);
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

    //edytuj film
    @GetMapping("/admin/edytuj-film/{id}")
    public String editMovie(@PathVariable long id,Model model){
        MovieDto movie = movieService.findMovieById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GenreDto> allGenres = genreService.findAllGenres();
        model.addAttribute("movie",movie);
        model.addAttribute("genres", allGenres);
        return "admin/edit-movie-form";
    }

    @PostMapping("/admin/edytuj-film")
    public String editMovie(MovieSaveDto movie, RedirectAttributes redirectAttributes){
        movieService.editMovie(movie);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został edytowany".formatted(movie.getTitle())
        );
        return "redirect:/admin";
    }

    //usun film

}
