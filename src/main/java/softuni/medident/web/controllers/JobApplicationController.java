package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.service.models.JobApplicationServiceModel;
import softuni.medident.service.services.JobApplicationService;
import softuni.medident.web.models.JobApplicationViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    private final ModelMapper modelMapper;

    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/careers")
    public ModelAndView getJobs(ModelAndView modelAndView){

        List<JobApplicationViewModel> jobs = this.jobApplicationService.getAllJobs()
                .stream()
                .map(j -> this.modelMapper.map(j, JobApplicationViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("jobs", jobs);
        modelAndView.setViewName("all-jobs.html");
        return modelAndView;
    }

    @GetMapping("/create-job")
    public String getCreateCareersForm(){
        return "/create-job.html";
    }

    @PostMapping("/create-job")
    public String create(@ModelAttribute JobApplicationViewModel viewModel){
        JobApplicationServiceModel serviceModel = this.modelMapper.map(viewModel, JobApplicationServiceModel.class);
        this.jobApplicationService.createJob(serviceModel);
        return "redirect:/careers";
    }




}
