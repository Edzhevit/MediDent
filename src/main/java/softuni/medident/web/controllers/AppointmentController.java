package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.service.models.AppointmentServiceModel;
import softuni.medident.service.models.DentistServiceModel;
import softuni.medident.service.models.TreatmentServiceModel;
import softuni.medident.service.models.UserProfileServiceModel;
import softuni.medident.service.services.AppointmentService;
import softuni.medident.service.services.DentistService;
import softuni.medident.service.services.TreatmentService;
import softuni.medident.service.services.UserService;
import softuni.medident.web.controllers.base.BaseController;
import softuni.medident.web.models.AppointmentViewModel;

import java.security.Principal;
import java.util.List;

@Controller
public class AppointmentController extends BaseController {

    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;
    private final DentistService dentistService;
    private final TreatmentService treatmentService;
    private final UserService userService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, ModelMapper modelMapper, DentistService dentistService, TreatmentService treatmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
        this.dentistService = dentistService;
        this.treatmentService = treatmentService;
        this.userService = userService;
    }

    @GetMapping("/appointment")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAppointmentForm(ModelAndView modelAndView) {
        List<DentistServiceModel> dentists = this.dentistService.getAll();
        List<TreatmentServiceModel> treatments = this.treatmentService.getAllTreatments();
        modelAndView.addObject("dentists", dentists);
        modelAndView.addObject("treatments", treatments);
        modelAndView.setViewName("appointments/add-appointment.html");
        return modelAndView;
    }

    @PostMapping("/appointment")
    @PreAuthorize("isAuthenticated()")
    public String makeAppointment(@ModelAttribute AppointmentViewModel createModel, Principal principal) {
        String username = principal.getName();
        AppointmentServiceModel userServiceModel = this.modelMapper.map(createModel, AppointmentServiceModel.class);
        UserProfileServiceModel userProfileServiceModel = this.userService.getByUsername(username);
        userServiceModel.setUser(userProfileServiceModel);
        this.appointmentService.makeAppointment(userServiceModel);

        return "redirect:/home";
    }
}
