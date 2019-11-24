package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;
import softuni.medident.service.services.AuthService;
import softuni.medident.web.models.LoginUserModel;
import softuni.medident.web.models.PatientRegisterModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;

    }

//    @ModelAttribute(value = "patient")
//    public PatientRegisterModel patientRegisterModel() {
//        return new PatientRegisterModel();
//    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("patient", new  PatientRegisterModel());
        return "/register.html";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "/login.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid PatientRegisterModel patient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/register";
        }

        model.addAttribute("patient", patient);

        PatientRegisterServiceModel patientServiceModel = this.modelMapper.map(model, PatientRegisterServiceModel.class);
        this.authService.registerPatient(patientServiceModel);
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String loginPatient(@ModelAttribute LoginUserModel user, HttpSession session) {
        PatientRegisterServiceModel serviceModel = this.modelMapper.map(user, PatientRegisterServiceModel.class);

        try {
            LoginUserServiceModel loginUserServiceModel = this.authService.login(serviceModel);
            session.setAttribute("user", loginUserServiceModel);
            return "redirect:/home";
        } catch (Exception ex) {
            return "redirect:/login";
        }
    }

}
