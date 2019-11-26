package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.JobApplication;
import softuni.medident.data.repositories.JobApplicationRepository;
import softuni.medident.service.models.JobApplicationServiceModel;
import softuni.medident.service.services.JobApplicationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createJob(JobApplicationServiceModel serviceModel) {
        JobApplication jobApplication = this.modelMapper.map(serviceModel, JobApplication.class);
        this.jobApplicationRepository.saveAndFlush(jobApplication);
    }

    @Override
    public List<JobApplicationServiceModel> getAllJobs(){
        return this.jobApplicationRepository.findAll()
                .stream()
                .map(job -> this.modelMapper.map(job, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }
}
