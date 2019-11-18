package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Dentist;
import softuni.medident.data.models.Patient;
import softuni.medident.data.repositories.DentistRepository;
import softuni.medident.data.repositories.PatientRepository;
import softuni.medident.service.models.DentistRegisterServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;
import softuni.medident.service.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthServiceImpl(PatientRepository patientRepository, DentistRepository dentistRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.dentistRepository = dentistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerPatient(PatientRegisterServiceModel patientModel) {

        //TODO Validate user

        Patient patient = this.modelMapper.map(patientModel, Patient.class);
        this.patientRepository.saveAndFlush(patient);
    }

    @Override
    public void registerDentist(DentistRegisterServiceModel dentistModel) {
        //TODO Validate user

        Dentist dentist = this.modelMapper.map(dentistModel, Dentist.class);
        this.dentistRepository.saveAndFlush(dentist);
    }
}
