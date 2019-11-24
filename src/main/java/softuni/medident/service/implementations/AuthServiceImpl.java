package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Patient;
import softuni.medident.data.repositories.PatientRepository;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;
import softuni.medident.service.services.AuthService;
import softuni.medident.service.services.HashService;

@Service
public class AuthServiceImpl implements AuthService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;

    @Autowired
    public AuthServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, HashService hashService) {
        this.patientRepository = patientRepository;
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
    public LoginUserServiceModel login(PatientRegisterServiceModel userServiceModel) throws Exception {
        String hashPassword = this.hashService.hash(userServiceModel.getPassword());
        return this.patientRepository
                .findByEmailAndPassword(userServiceModel.getEmail(), hashPassword)
                .map(user -> new LoginUserServiceModel(userServiceModel.getEmail()))
                .orElseThrow(() -> new Exception("Invalid User"));

    }
}
