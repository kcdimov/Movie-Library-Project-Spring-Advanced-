package bg.softuni.movies.web;

import bg.softuni.movies.models.bindings.PictureAddBM;
import bg.softuni.movies.models.service.PictureServiceModel;
import bg.softuni.movies.services.ActorService;
import bg.softuni.movies.services.MovieService;
import bg.softuni.movies.services.PictureService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/pictures")
public class PictureController {

    public final PictureService pictureService;
    public final ModelMapper modelMapper;
    public final MovieService movieService;
    public final ActorService actorService;

    public PictureController(PictureService pictureService, ModelMapper modelMapper,
                             MovieService movieService, ActorService actorService) {
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @ModelAttribute("pictureAddBM")
    public PictureAddBM pictureAddBM() {
        return new PictureAddBM();
    }

    @GetMapping("/add-picture")
    public String addPicture(Model model) {

        if (!model.containsAttribute("pictureAddBM")) {
            model.addAttribute("pictureAddBM", new PictureAddBM());
        }

        model.addAttribute("movieTitles", this.movieService.getAllMovies());
        model.addAttribute("actorNames", this.actorService.getAllActors());

        return "add-picture";
    }

    @PostMapping("/add-picture")
    public String addPicture(@Valid PictureAddBM pictureAddBM, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("pictureAddBM", pictureAddBM);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.pictureAddBM", bindingResult);
            return "redirect:add-picture";
        }

        PictureServiceModel pictureServiceModel = this.modelMapper.map(pictureAddBM, PictureServiceModel.class);
        this.pictureService.addPicture(pictureServiceModel, pictureAddBM.getMovie(), pictureAddBM.getActorName());

        return "home";
    }


}
