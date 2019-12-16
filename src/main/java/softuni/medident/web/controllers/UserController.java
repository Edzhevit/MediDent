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
import softuni.medident.service.services.CloudinaryService;
import softuni.medident.service.services.UserService;
import softuni.medident.web.models.UserProfileModel;
import softuni.medident.web.models.UserRegisterModel;

import java.io.IOException;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
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
    public String register(@ModelAttribute UserRegisterModel user, BindingResult bindingResult) throws IOException {
        UserRegisterServiceModel userServiceModel = this.modelMapper.map(user, UserRegisterServiceModel.class);
//        userServiceModel.setImageUrl(this.cloudinaryService.upload(user.getImage()));
//        if (bindingResult.hasErrors()) {
//            return "auth/register.html";
//        }

        try {
            this.userService.register(userServiceModel);
        } catch (UserNotFoundException | Exception e) {
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public ModelAndView getProfile(ModelAndView modelAndView, Principal principal){
        String username = principal.getName();
        UserProfileServiceModel serviceModel = this.userService.getProfile(username);
        UserProfileModel viewModel = this.modelMapper.map(serviceModel, UserProfileModel.class);
        modelAndView.addObject("model", viewModel);
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
