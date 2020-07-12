package edu.miu.cs545.group5.onlinemarket.controller.auth;

import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {
    static final String SIGN_UP_VIEW = "registration";

    @Autowired
    UserService userService;

    @GetMapping
    public String registration(Principal user, Model model) {
        if (user!=null)
            return "redirect:/home";
        model.addAttribute("user", new User());
        return SIGN_UP_VIEW;
    }

    @PostMapping
    public String registrationSubmit(@ModelAttribute("user") @Valid User user, BindingResult br) {
        if (br.hasErrors()) {
            return SIGN_UP_VIEW;
        }
        if (userService.getByEmail(user.getEmail()).isPresent()) {
            br.rejectValue("email", "error.user",
                    "There is already one registered with the email provided");
            return SIGN_UP_VIEW;
        }
        userService.save(user);
        return "redirect:/login";
    }
}
