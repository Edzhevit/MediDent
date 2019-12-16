package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.UserProfileServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.UserService;
import softuni.medident.web.models.UserProfileModel;
import softuni.medident.web.models.UserRegisterModel;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
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
            this.userService.register(userServiceModel);
        } catch (UserNotFoundException | Exception e) {
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public ModelAndView getProfile(ModelAndView modelAndView, UserProfileModel user){
        UserProfileServiceModel serviceModel = this.modelMapper.map(user, UserProfileServiceModel.class);
        modelAndView.setViewName("user/profile.html");
        return modelAndView;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleException(UserNotFoundException exception){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}
