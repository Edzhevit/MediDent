package softuni.medident.service.services;

import softuni.medident.service.models.DentistRegisterServiceModel;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;

public interface AuthService {

    void registerPatient (PatientRegisterServiceModel patient);

    void registerDentist (DentistRegisterServiceModel dentist);

    LoginUserServiceModel login(PatientRegisterServiceModel patient) throws Exception;

    LoginUserServiceModel login(DentistRegisterServiceModel dentist);

}
