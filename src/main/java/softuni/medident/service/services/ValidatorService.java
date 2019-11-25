package softuni.medident.service.services;

import softuni.medident.service.models.PatientRegisterServiceModel;

public interface ValidatorService {

    Boolean isValid(PatientRegisterServiceModel object);

}
