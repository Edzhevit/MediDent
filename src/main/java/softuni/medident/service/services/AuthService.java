package softuni.medident.service.services;

import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;

public interface AuthService {

    void registerPatient (PatientRegisterServiceModel patient) throws Exception;

    LoginUserServiceModel login(PatientRegisterServiceModel patient) throws Exception;


}
