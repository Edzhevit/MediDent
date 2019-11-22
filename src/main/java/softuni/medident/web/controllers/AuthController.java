package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.medident.service.models.DentistRegisterServiceModel;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;
import softuni.medident.service.services.AuthService;
import softuni.medident.web.models.DentistRegisterModel;
import softuni.medident.web.models.LoginUserModel;
import softuni.medident.web.models.PatientRegisterModel;

import javax.servlet.http.HttpSession;

@Controller
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
        return "/register.html";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "/login.html";
    }

    @PostMapping("/patient/register")
    public String register(@ModelAttribute("patient") PatientRegisterModel patient) {
        PatientRegisterServiceModel patientServiceModel = this.modelMapper.map(patient, PatientRegisterServiceModel.class);
        this.authService.registerPatient(patientServiceModel);
        return "redirect:/patient/login";
    }

    @PostMapping("/dentist/register")
    public String register(@ModelAttribute DentistRegisterModel dentist) {
        DentistRegisterServiceModel dentistServiceModel = this.modelMapper.map(dentist, DentistRegisterServiceModel.class);
        this.authService.registerDentist(dentistServiceModel);
        return "redirect:/dentist/login";
    }

    @PostMapping("/patient/login")
    public String loginPatient(@ModelAttribute LoginUserModel user, HttpSession session){
        PatientRegisterServiceModel serviceModel = this.modelMapper.map(user, PatientRegisterServiceModel.class);

        try {
            LoginUserServiceModel loginUserServiceModel = this.authService.login(serviceModel);
            session.setAttribute("user", loginUserServiceModel);
            return "redirect:/home";
        } catch (Exception ex ){
            return "redirect:/patient/login";
        }
    }

    @PostMapping("/dentist/login")
    public String loginDentist(@ModelAttribute LoginUserModel user, HttpSession session){
        DentistRegisterServiceModel serviceModel = this.modelMapper.map(user, DentistRegisterServiceModel.class);

        try {
            LoginUserServiceModel loginUserServiceModel = this.authService.login(serviceModel);
            session.setAttribute("user", loginUserServiceModel);
            return "redirect:/home";
        } catch (Exception ex ){
            return "redirect:/dentist/login";
        }
    }
}
