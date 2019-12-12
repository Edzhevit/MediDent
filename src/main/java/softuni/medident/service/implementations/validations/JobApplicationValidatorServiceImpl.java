package softuni.medident.service.implementations.validations;

import org.springframework.stereotype.Service;
import softuni.medident.service.models.JobApplicationServiceModel;
import softuni.medident.service.services.JobApplicationValidatorService;

@Service
public class JobApplicationValidatorServiceImpl implements JobApplicationValidatorService {

    @Override
    public boolean isValid(JobApplicationServiceModel serviceModel) {
        return serviceModel != null;
    }
}
