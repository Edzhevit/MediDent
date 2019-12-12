package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.service.models.TreatmentServiceModel;
import softuni.medident.service.services.TreatmentService;
import softuni.medident.web.models.TreatmentViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public TreatmentController(TreatmentService treatmentService, ModelMapper modelMapper) {
        this.treatmentService = treatmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ModelAndView getTreatments(ModelAndView modelAndView){

        List<TreatmentViewModel> treatments = this.treatmentService.getAllTreatments()
                .stream()
                .map(t -> this.modelMapper.map(t, TreatmentViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("treatments", treatments);
        modelAndView.setViewName("treatments/all-treatments.html");
        return modelAndView;
    }

    @GetMapping("/create")
    public String getCreateTreatmentForm(){
        return "treatments/create-treatment.html";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute TreatmentViewModel viewModel) {
        TreatmentServiceModel serviceModel = this.modelMapper.map(viewModel, TreatmentServiceModel.class);
        this.treatmentService.createTreatment(serviceModel);
        return "redirect:/treatments/all";
    }
}
