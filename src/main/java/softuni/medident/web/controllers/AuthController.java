package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.AuthService;
import softuni.medident.web.models.LoginUserModel;
import softuni.medident.web.models.UserRegisterModel;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;

    }

    @GetMapping("/register")
    public String getRegisterForm() {
        return "auth/register.html";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "auth/login.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register.html";
        }

        UserRegisterServiceModel userServiceModel = this.modelMapper.map(user, UserRegisterServiceModel.class);
        try {
            this.authService.registerUser(userServiceModel);
        } catch (Exception e) {
            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute LoginUserModel user, HttpSession session) {
        UserRegisterServiceModel serviceModel = this.modelMapper.map(user, UserRegisterServiceModel.class);

        try {
            LoginUserServiceModel loginUserServiceModel = this.authService.login(serviceModel);
            session.setAttribute("user", loginUserServiceModel);
            return "redirect:/home";
        } catch (Exception ex) {
            return "redirect:/users/login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "home/index.html";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleException(UserNotFoundException exception){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}
