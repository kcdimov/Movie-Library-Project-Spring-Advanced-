package bg.softuni.movies.web;

import bg.softuni.movies.models.bindings.ReviewAddBM;
import bg.softuni.movies.models.service.ReviewServiceModel;
import bg.softuni.movies.services.AppUser;
import bg.softuni.movies.services.AppUserDetailsService;
import bg.softuni.movies.services.MovieService;
import bg.softuni.movies.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieService movieService;
    private final ModelMapper modelMapper;


    public ReviewController(ReviewService reviewService, MovieService movieService, ModelMapper modelMapper) {
        this.reviewService = reviewService;
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("reviewAddBM")
    public ReviewAddBM reviewAddBM() {
        return new ReviewAddBM();
    }
    @GetMapping("/add-review")
    public String addReview(Model model) {

        if (!model.containsAttribute("reviewAddBM")) {
            model.addAttribute("reviewAddBM", ReviewAddBM.class);
        }
        model.addAttribute("movieTitles", this.movieService.getAllTitleOfMovies());

        return "add-review";
    }

    @PostMapping("/add-review")
    public String addReview(@Valid ReviewAddBM reviewAddBM, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetail) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("reviewAddBM", reviewAddBM);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.reviewAddBM", bindingResult);
            return "redirect:add-review";
        }

        ReviewServiceModel reviewServiceModel = this.modelMapper.map(reviewAddBM, ReviewServiceModel.class);
        this.reviewService.addReview(reviewServiceModel, reviewAddBM.getMovie(), userDetail);


        return "home";
    }
}
