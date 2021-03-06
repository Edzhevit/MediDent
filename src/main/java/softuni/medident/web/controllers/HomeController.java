package softuni.medident.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public String index(){
        return "home/index.html";
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("username", principal.getName());
        modelAndView.setViewName("home/home.html");
        return modelAndView;
    }
}
