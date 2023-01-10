package bg.softuni.movies.web;


import bg.softuni.movies.models.entity.Review;
import bg.softuni.movies.repository.ReviewRepository;
import bg.softuni.movies.services.ActorService;
import bg.softuni.movies.services.MovieService;
import bg.softuni.movies.services.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class HomeController {

    private final MovieService movieService;
    private final ActorService actorService;
    private final PictureService pictureService;
    private final ReviewRepository reviewRepository;

    //TODO add director and composer

    public HomeController(MovieService movieService, ActorService actorService,
                          PictureService pictureService, ReviewRepository reviewRepository) {
        this.movieService = movieService;
        this.actorService = actorService;
        this.pictureService = pictureService;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {

        List<Review> reviews = this.reviewRepository.findAll();
        //TODO change moviesAll with allMovies
        model.addAttribute("allMovies", this.movieService.getAllMovies());
        model.addAttribute("reviews", reviews);

        return "home";
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
        model.addAttribute("movies", this.movieService.getAllMovies());
        model.addAttribute("actors", this.actorService.getAllActors());
        model.addAttribute("name", httpSession.getAttribute("name"));

        return "index";
    }
}
