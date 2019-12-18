package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.JobApplication;
import softuni.medident.data.repositories.JobApplicationRepository;
import softuni.medident.exception.JobNotFoundException;
import softuni.medident.service.models.JobApplicationServiceModel;
import softuni.medident.service.services.JobApplicationService;
import softuni.medident.service.services.JobApplicationValidatorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final static String NO_SUCH_JOB_MESSAGE = "There is no such job application!";

    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationValidatorService validatorService;
    private final ModelMapper modelMapper;

    @Autowired
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, JobApplicationValidatorService validatorService, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.validatorService = validatorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createJob(JobApplicationServiceModel serviceModel) throws JobNotFoundException {
        if (!validatorService.isValid(serviceModel)){
            throw new JobNotFoundException(NO_SUCH_JOB_MESSAGE);
        }
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
            throw new JobNotFoundException(NO_SUCH_JOB_MESSAGE);
        }

        return this.modelMapper.map(jobApplication, JobApplicationServiceModel.class);
    }

    @Override
    public void removeJob(String id) throws JobNotFoundException {
        JobApplication jobApplication = this.jobApplicationRepository.getById(id);
        if (jobApplication == null){
            throw new JobNotFoundException(NO_SUCH_JOB_MESSAGE);
        }

        this.jobApplicationRepository.delete(jobApplication);
    }
}
