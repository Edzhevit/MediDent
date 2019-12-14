package softuni.medident.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String getIndex(){
        return "home/index.html";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home/home.html";
    }
}
