package softuni.medident.service.services;

import softuni.medident.exception.JobNotFoundException;
import softuni.medident.service.models.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {

    void createJob(JobApplicationServiceModel serviceModel) throws JobNotFoundException;

    List<JobApplicationServiceModel> getAllJobs();

    JobApplicationServiceModel getById(String id) throws JobNotFoundException;

    void removeJob(String id) throws JobNotFoundException;
}
