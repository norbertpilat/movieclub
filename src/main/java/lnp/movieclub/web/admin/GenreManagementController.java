package lnp.movieclub.web.admin;

import lnp.movieclub.genre.GenreService;
import lnp.movieclub.genre.dto.GenreDto;
import lnp.movieclub.genre.dto.GenreSaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GenreManagementController {
    private final GenreService genreService;

    public GenreManagementController(GenreService genreService) {
        this.genreService = genreService;
    }
    //dodawanie gatunku
    @GetMapping("/admin/dodaj-gatunek")
    public String addGenreForm(Model model){
        GenreDto genre = new GenreDto();
        model.addAttribute("genre",genre);
        return "admin/genre-form";
    }
    //dodawanie gatunku
    @PostMapping("/admin/dodaj-gatunek")
    public String addGenre(GenreDto genreDto, RedirectAttributes redirectAttributes){
        if (genreService.findGenreByName(genreDto.getName()).isPresent()){
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    "Gatunek %s już istnieje".formatted(genreDto.getName())
            );
        }else {
            genreService.addGenre(genreDto);
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    "Gatunek %s zistał zapisany".formatted(genreDto.getName())
            );
        }
        return "redirect:/admin";
    }
    //edytowanie gatunku
    @GetMapping("/admin/edytuj-gatunek/{name}")
    public String editGenreForm(@PathVariable String name, Model model){
        GenreDto genre = genreService.findGenreByName(name)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("genre",genre);
        return "admin/edit-genre-form";
    }
    //edytowanie gatunku
    @PostMapping("/admin/edytuj-gatunek")
    public String editGenre(GenreSaveDto genreDto, RedirectAttributes redirectAttributes){
        genreService.editGenre(genreDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został edytowany".formatted(genreDto.getName())
        );
        return "redirect:/admin";
    }
}
