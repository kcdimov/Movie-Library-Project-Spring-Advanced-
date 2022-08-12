package bg.softuni.movies.web;

import bg.softuni.movies.models.bindings.SearchMovieBM;
import bg.softuni.movies.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SearchController {

    public final MovieService movieService;

    public SearchController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search/search/{genre}")
    public String search(@PathVariable("genre") String genre, Model model) {

        if (!model.containsAttribute("searchMovieBM")){
            model.addAttribute("searchMovieBM", new SearchMovieBM());
        }

        if (!genre.equals("genre")) {
            model.addAttribute("movies", this.movieService.getAllMoviesByGenre(genre));
        }

        return "search";
    }

    @PostMapping("/search/genre")
    public String searchPost(@Valid SearchMovieBM searchMovieBM, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchMovieBM", searchMovieBM);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.searchMovieBM", bindingResult);

            return "redirect:search/genre";
        }


        return "redirect:search/" + searchMovieBM.getGenre();
    }
}
