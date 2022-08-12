package bg.softuni.movies.web;

import bg.softuni.movies.models.bindings.ActorAddBM;
import bg.softuni.movies.models.entity.Actor;
import bg.softuni.movies.models.entity.Picture;
import bg.softuni.movies.models.service.ActorServiceModel;
import bg.softuni.movies.services.ActorService;
import bg.softuni.movies.services.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/actors")
public class ActorController {

        private final ActorService actorService;
        private final ModelMapper modelMapper;
        private final PictureService pictureService;

    public ActorController(ActorService actorService, ModelMapper modelMapper, PictureService pictureService) {
        this.actorService = actorService;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
    }

    @ModelAttribute()
    public ActorAddBM actorAddBM() {
        return new ActorAddBM();
    }

    @GetMapping("/add-actor")
    public String addActor(Model model) {

        if (!model.containsAttribute("actorAddBM")) {
            model.addAttribute("actorAddBM", new ActorAddBM());
        }



        return "add-actor";
    }

    @PostMapping("/add-actor")
    public String addActor(@Valid ActorAddBM actorAddBM, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("actorAddBM", actorAddBM);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.actorAddBM",
                    bindingResult);
            return "redirect:add-actor";
        }

        this.actorService.addActor(this.modelMapper.map(actorAddBM, ActorServiceModel.class));

//        if (this.actorService.findActorsByNames(actorAddBM().getName()) != null) {
//            ActorServiceModel actor = this.actorService.findActorsByNames(actorAddBM().getName());
//            redirectAttributes.addFlashAttribute("actor", actor);
//            return "redirect:/actor-details/" + actor.getId();
//        }

        return "redirect:/actors/all-actors";

    }


    @GetMapping("/actor-details/{id}")
    public String actorDetails(@PathVariable Long id, Model model) {

        ActorServiceModel actorId = this.actorService.findById(id);

        Optional<Picture> allPicturesByActor = this.pictureService.getAllPicturesByActor(id);

        String actorPicture = this.pictureService.getActorRandomPicture(id);
        model.addAttribute("actor", actorId);
        model.addAttribute("picture", actorPicture);
        model.addAttribute("pictureChange", allPicturesByActor);

        return "actor-details";
    }

    @GetMapping("/all-actors")
    public String getAllActors(Model model) {

        List<Actor> allActors = this.actorService.getAllActors();
        model.addAttribute("actors", allActors);

        return "all-actors";
    }

}
