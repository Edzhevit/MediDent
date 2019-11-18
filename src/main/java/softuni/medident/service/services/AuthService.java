package softuni.medident.service.services;

import softuni.medident.service.models.DentistRegisterServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;

public interface AuthService {

    void registerPatient (PatientRegisterServiceModel patient);

    void registerDentist (DentistRegisterServiceModel dentist);

}
