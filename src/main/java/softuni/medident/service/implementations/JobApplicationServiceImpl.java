package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.JobApplication;
import softuni.medident.data.repositories.JobApplicationRepository;
import softuni.medident.exception.JobNotFoundException;
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

    @Override
    public JobApplicationServiceModel getById(String id) throws JobNotFoundException {
        JobApplication jobApplication = this.jobApplicationRepository.getById(id);

        if (jobApplication == null){
            throw new JobNotFoundException("Job application with such name does not exist");
        }

        return this.modelMapper.map(jobApplication, JobApplicationServiceModel.class);
    }

    @Override
    public void removeJob(String id) {
        JobApplication jobApplication = this.jobApplicationRepository.getById(id);

        this.jobApplicationRepository.delete(jobApplication);
    }
}
