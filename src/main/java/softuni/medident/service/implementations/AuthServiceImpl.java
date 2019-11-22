package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Dentist;
import softuni.medident.data.models.Patient;
import softuni.medident.data.repositories.DentistRepository;
import softuni.medident.data.repositories.PatientRepository;
import softuni.medident.service.models.DentistRegisterServiceModel;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;
import softuni.medident.service.services.AuthService;
import softuni.medident.service.services.HashService;

@Service
public class AuthServiceImpl implements AuthService {

    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;

    @Autowired
    public AuthServiceImpl(PatientRepository patientRepository, DentistRepository dentistRepository, ModelMapper modelMapper, HashService hashService) {
        this.patientRepository = patientRepository;
        this.dentistRepository = dentistRepository;
        this.modelMapper = modelMapper;
        this.hashService = hashService;
    }

    @Override
    public void registerPatient(PatientRegisterServiceModel patientModel) {

        //TODO Validate user

        Patient patient = this.modelMapper.map(patientModel, Patient.class);
        patient.setPassword(this.hashService.hash(patient.getPassword()));
        this.patientRepository.saveAndFlush(patient);
    }

    @Override
    public void registerDentist(DentistRegisterServiceModel dentistModel) {
        //TODO Validate user

        Dentist dentist = this.modelMapper.map(dentistModel, Dentist.class);
        dentist.setPassword(this.hashService.hash(dentist.getPassword()));
        this.dentistRepository.saveAndFlush(dentist);
    }

    @Override
    public LoginUserServiceModel login(PatientRegisterServiceModel userServiceModel) throws Exception {
        String hashPassword = this.hashService.hash(userServiceModel.getPassword());
        return this.patientRepository
                .findByEmailAndPassword(userServiceModel.getEmail(), hashPassword)
                .map(user -> new LoginUserServiceModel(userServiceModel.getEmail()))
                .orElseThrow(() -> new Exception("Invalid User"));

    }

    @Override
    public LoginUserServiceModel login(DentistRegisterServiceModel dentist) {
        return null;
    }
}
