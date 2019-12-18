package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.exception.JobNotFoundException;
import softuni.medident.service.models.JobApplicationServiceModel;
import softuni.medident.service.services.JobApplicationService;
import softuni.medident.web.models.JobApplicationViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/careers")
public class JobApplicationController {

    public final static String JOB_DETAILS_VIEW_NAME = "careers/job-details.html";

    private final JobApplicationService jobApplicationService;
    private final ModelMapper modelMapper;

    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getJobs(ModelAndView modelAndView){

        List<JobApplicationViewModel> jobs = this.jobApplicationService.getAllJobs()
                .stream()
                .map(j -> this.modelMapper.map(j, JobApplicationViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("jobs", jobs);
        modelAndView.setViewName("careers/all-jobs.html");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create")
    public String getCreateCareersForm(){
        return "careers/add-job.html";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute JobApplicationViewModel viewModel) throws JobNotFoundException {
        JobApplicationServiceModel serviceModel = this.modelMapper.map(viewModel, JobApplicationServiceModel.class);
        this.jobApplicationService.createJob(serviceModel);
        return "redirect:/careers/all";
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getJobDetails(@PathVariable String id, ModelAndView modelAndView) throws JobNotFoundException {
        JobApplicationServiceModel serviceModel = jobApplicationService.getById(id);
        JobApplicationViewModel viewModel = this.modelMapper.map(serviceModel, JobApplicationViewModel.class);
        modelAndView.addObject("job", viewModel);
        modelAndView.setViewName(JOB_DETAILS_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteJob(@PathVariable String id) throws JobNotFoundException {
        this.jobApplicationService.removeJob(id);
        return "redirect:/careers/all";
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ModelAndView handleException(JobNotFoundException exception){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}
