package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final JobApplicationService jobApplicationService;
    private final ModelMapper modelMapper;

    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ModelAndView getJobs(ModelAndView modelAndView){

        List<JobApplicationViewModel> jobs = this.jobApplicationService.getAllJobs()
                .stream()
                .map(j -> this.modelMapper.map(j, JobApplicationViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("jobs", jobs);
        modelAndView.setViewName("careers/all-jobs.html");
        return modelAndView;
    }

    @GetMapping("/create-job")
    public String getCreateCareersForm(){
        return "careers/create-job.html";
    }

    @PostMapping("/create-job")
    public String create(@ModelAttribute JobApplicationViewModel viewModel){
        JobApplicationServiceModel serviceModel = this.modelMapper.map(viewModel, JobApplicationServiceModel.class);
        this.jobApplicationService.createJob(serviceModel);
        return "redirect:/careers/all";
    }

    @GetMapping("/details/{id}")
    public ModelAndView getJobDetails(@PathVariable String id, ModelAndView modelAndView) throws JobNotFoundException {
        JobApplicationServiceModel serviceModel = jobApplicationService.getById(id);
        JobApplicationViewModel viewModel = this.modelMapper.map(serviceModel, JobApplicationViewModel.class);
        modelAndView.addObject("job", viewModel);
        modelAndView.setViewName("careers/job-details.html");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteJob(@PathVariable String id) {
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
