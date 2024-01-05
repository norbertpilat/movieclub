package lnp.movieclub.web;

import lnp.movieclub.genre.GenreService;
import lnp.movieclub.genre.dto.GenreDto;
import lnp.movieclub.movie.MovieService;
import lnp.movieclub.movie.dto.MovieDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@AllArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final MovieService movieService;

    @GetMapping("/gatunek/{name}")
    public String getGenre(@PathVariable String name, Model model) {
        GenreDto genre = genreService.findGenreByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<MovieDto> movies = movieService.findMoviesByGenreName(name);
        model.addAttribute("heading", genre.getName());
        model.addAttribute("description", genre.getDescription());
        model.addAttribute("movies", movies);
        return "movie-listing";
    }

    @GetMapping("/gatunki-filmowe")
    public String getGenreList(Model model){
        List<GenreDto> genre = genreService.findAllGenres();
        model.addAttribute("genres",genre);
        return "genre-listing";
    }
}
