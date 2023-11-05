package lnp.movieclub.web.admin;

import lnp.movieclub.genre.GenreService;
import lnp.movieclub.genre.dto.GenreDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GenreManagementController {
    private final GenreService genreService;

    public GenreManagementController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/admin/dodaj-gatunek")
    public String addGenreForm(Model model){
        GenreDto genre = new GenreDto();
        model.addAttribute("genre",genre);
        return "admin/genre-form";
    }

    @PostMapping("/admin/dodaj-gatunek")
    public String addGenre(GenreDto genreDto, RedirectAttributes redirectAttributes){
        genreService.addGenre(genreDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s zistał zapisany".formatted(genreDto.getName())
        );
        return "redirect:/admin";
    }
}
