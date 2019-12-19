package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.exception.JobNotFoundException;
import softuni.medident.exception.TreatmentNotFoundException;
import softuni.medident.service.models.TreatmentServiceModel;
import softuni.medident.service.services.TreatmentService;
import softuni.medident.web.models.TreatmentViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/treatments")
public class TreatmentController {

    public final static String TREATMENT_DETAILS_VIEW_NAME = "treatments/treatment-details.html";

    private final TreatmentService treatmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public TreatmentController(TreatmentService treatmentService, ModelMapper modelMapper) {
        this.treatmentService = treatmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getTreatments(ModelAndView modelAndView){

        List<TreatmentViewModel> treatments = this.treatmentService.getAllTreatments()
                .stream()
                .map(t -> this.modelMapper.map(t, TreatmentViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("treatments", treatments);
        modelAndView.setViewName("treatments/all-treatments.html");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create")
    public String getCreateTreatmentForm(){
        return "treatments/add-treatment.html";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute TreatmentViewModel viewModel) {
        TreatmentServiceModel serviceModel = this.modelMapper.map(viewModel, TreatmentServiceModel.class);
        this.treatmentService.createTreatment(serviceModel);
        return "redirect:/treatments/all";
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getTreatmentDetails(@PathVariable String id, ModelAndView modelAndView) {
        TreatmentServiceModel serviceModel = treatmentService.getById(id);
        TreatmentViewModel treatment = this.modelMapper.map(serviceModel, TreatmentViewModel.class);
        modelAndView.addObject("treatment", treatment);
        modelAndView.setViewName(TREATMENT_DETAILS_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteJob(@PathVariable String id) throws TreatmentNotFoundException {
        this.treatmentService.removeTreatment(id);
        return "redirect:/treatments/all";
    }
}
