package softuni.medident.service.services;

import softuni.medident.service.models.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {

    void createJob(JobApplicationServiceModel serviceModel);

    List<JobApplicationServiceModel> getAllJobs();
}
