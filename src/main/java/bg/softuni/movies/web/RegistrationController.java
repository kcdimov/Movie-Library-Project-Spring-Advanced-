package bg.softuni.movies.web;

import bg.softuni.movies.models.bindings.UserRegisterBM;
import bg.softuni.movies.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterBM")
    public UserRegisterBM userRegisterBM() {
        return new UserRegisterBM();
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("userRegisterBM")) {
            model.addAttribute("userRegisterBM", new UserRegisterBM());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid   UserRegisterBM userRegisterBM,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBM.getPassword()
                .equals(userRegisterBM.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBM", userRegisterBM);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBM",
                            bindingResult);
            return "redirect:/register";
        }
        try {
            this.userService.findByUsername(userRegisterBM.getUsername());
            bindingResult.rejectValue("username", "error.user", "Username already exists!");
        } catch (Exception e) {
        }



        userService.registerAndLogin(userRegisterBM);

        return "redirect:/home";
    }


}
