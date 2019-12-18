package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.service.models.DentistServiceModel;
import softuni.medident.service.services.CloudinaryService;
import softuni.medident.service.services.DentistService;
import softuni.medident.web.controllers.base.BaseController;
import softuni.medident.web.models.DentistViewDetailsModel;
import softuni.medident.web.models.DentistViewModel;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dentists/")
public class DentistController extends BaseController {

    private final DentistService dentistService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public DentistController(DentistService dentistService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.dentistService = dentistService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create")
    public String getCreateDentistForm(){
        return "dentists/add-dentist.html";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public String createProduct(@ModelAttribute DentistViewModel dentist) throws IOException {
        DentistServiceModel serviceModel = this.modelMapper.map(dentist, DentistServiceModel.class);
        serviceModel.setImageUrl(this.cloudinaryService.upload(dentist.getImage()));
        this.dentistService.create(serviceModel);
        return "redirect:/dentists/all";
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getDentists(ModelAndView modelAndView){

        List<DentistViewDetailsModel> dentists = this.dentistService.getAll()
                .stream()
                .map(d -> this.modelMapper.map(d, DentistViewDetailsModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("dentists", dentists);
        modelAndView.setViewName("dentists/all-dentists.html");
        return modelAndView;
    }
}
