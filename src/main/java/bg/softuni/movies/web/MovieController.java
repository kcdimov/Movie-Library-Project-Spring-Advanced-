package bg.softuni.movies.web;

import bg.softuni.movies.models.bindings.MovieAddBM;
import bg.softuni.movies.models.entity.Movie;
import bg.softuni.movies.models.entity.Review;
import bg.softuni.movies.models.service.MovieServiceModel;
import bg.softuni.movies.repository.ReviewRepository;
import bg.softuni.movies.services.ActorService;
import bg.softuni.movies.services.MovieService;
import bg.softuni.movies.services.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final ModelMapper modelMapper;
    private final ActorService actorService;
    private final PictureService pictureService;
    private final ReviewRepository reviewRepository;


    public MovieController(MovieService movieService, ModelMapper modelMapper, ActorService actorService, PictureService pictureService, ReviewRepository reviewRepository) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
        this.actorService = actorService;
        this.pictureService = pictureService;
        this.reviewRepository = reviewRepository;
    }

    @ModelAttribute("movieAddBM")
    public MovieAddBM movieAddBM() {
        return new MovieAddBM();
    }

    @GetMapping("/all-movies")
    public String getAllMovies(Model model) {

        model.addAttribute("movies", this.movieService.getAllMovies());

        return "all-movies";
    }

//    @PreAuthorize("hasAuthority('ROlE_ADMIN')")
    @GetMapping("/add-movie")
    public String addMovie(Model model) {
        System.out.println("Form add - movie");
        if (!model.containsAttribute("movieAddBM")) {
            model.addAttribute("movieAddBM", new MovieAddBM());
        }

        model.addAttribute("actorNames", actorService.findAllActorsNames());
        return "add-movie";
    }

    @PostMapping("/add-movie")
    public String addMovie(@Valid MovieAddBM movieAddBM, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("movieAddBM", movieAddBM);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.movieAddBM",
                    bindingResult);
            return "add-movie";
        }

        model.addAttribute("actorNames", this.actorService.findAllActorsNames());

        MovieServiceModel movieServiceModel = this.modelMapper.map(movieAddBM, MovieServiceModel.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        this.movieService.addMovie(movieServiceModel, movieAddBM, currentPrincipalName);

        Movie getMovieByTitle = this.movieService.findMovieByTitle(movieServiceModel.getTitle());

        redirectAttributes.addFlashAttribute("movie", getMovieByTitle);

        return "redirect:movie-details/" + getMovieByTitle.getId();
    }

    @GetMapping("/movie-details/{id}")
    public String movieDetails(@PathVariable("id") Long id, Model model) {

        MovieServiceModel movieServiceModel = this.movieService.findById(id);

        List<Review> reviews = this.reviewRepository.findAllByMovie_Id(id);
        String pictureOfMovie = this.pictureService.getRandomPictureOfMovie(id);
        model.addAttribute("movie", movieServiceModel);
        model.addAttribute("picture", pictureOfMovie);
        model.addAttribute("reviews", reviews);

        return "movie-details";
    }


    @DeleteMapping("/movie-details/{id}")
    public String deleteMovieById(@PathVariable("id") Long id) {

        movieService.deleteMovieById(id);

        return "redirect:/movies/all-movies";
    }

    @GetMapping("/my-movies")
    public String myMovies(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();

        List<Movie> allMoviesByUser = this.movieService.getAllMoviesByUser(currentPrincipal);

        //TODO first to add movie to DB
        if (allMoviesByUser.size() != 0) {
            model.addAttribute("movies", this.movieService.getAllMoviesByUser(currentPrincipal));
        }


        return "my-movies";
    }



}
