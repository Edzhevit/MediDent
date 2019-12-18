package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.AddressServiceModel;
import softuni.medident.service.models.UserProfileServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.CloudinaryService;
import softuni.medident.service.services.UserService;
import softuni.medident.web.controllers.base.BaseController;
import softuni.medident.web.models.*;

import java.io.IOException;
import java.security.Principal;

@Controller
public class UserController extends BaseController {

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
    @PreAuthorize("isAnonymous()")
    public String getRegisterForm() {
        return "auth/register.html";
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String getLoginForm() {
        return "auth/login.html";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String register(@ModelAttribute UserRegisterModel user, BindingResult bindingResult) throws IOException {
        UserRegisterServiceModel userServiceModel = this.modelMapper.map(user, UserRegisterServiceModel.class);
        if (bindingResult.hasErrors()) {
            return "auth/register.html";
        }

        try {
            this.userService.register(userServiceModel);
        } catch (UserNotFoundException | Exception e) {
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getProfile(ModelAndView modelAndView, Principal principal){
        String username = principal.getName();
        UserProfileServiceModel serviceModel = this.userService.getProfile(username);
        UserProfileModel viewModel = this.modelMapper.map(serviceModel, UserProfileModel.class);
        modelAndView.addObject("model", viewModel);
        modelAndView.setViewName("user/profile.html");
        return modelAndView;
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public String edit() {
        return "user/edit.html";
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(@ModelAttribute UserEditModel model, Principal principal) throws IOException {
        String username = principal.getName();
        UserProfileServiceModel userProfileServiceModel = this.userService.getByUsername(username);
        userProfileServiceModel.setImageUrl(this.cloudinaryService.upload(model.getImage()));
        userProfileServiceModel.setAge(model.getAge());
        userProfileServiceModel.setPhoneNumber(model.getPhoneNumber());
        this.userService.editProfile(userProfileServiceModel);

        return redirect("/profile");
    }

    @GetMapping("/address")
    @PreAuthorize("isAuthenticated()")
    public String addAddress() {
        return "user/address.html";
    }

    @PostMapping("/address")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addAddress(@ModelAttribute AddressViewModel model, Principal principal) throws IOException {
        String username = principal.getName();
        UserProfileServiceModel userProfileServiceModel = this.userService.getByUsername(username);
        AddressServiceModel address = this.modelMapper.map(model, AddressServiceModel.class);
        userProfileServiceModel.setAddress(address);
        this.userService.addAddress(userProfileServiceModel, address);

        return redirect("/profile");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleException(UserNotFoundException exception){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}
