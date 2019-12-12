package softuni.medident.service.services;

import softuni.medident.service.models.JobApplicationServiceModel;

public interface JobApplicationValidatorService {

    boolean isValid(JobApplicationServiceModel serviceModel);
}
