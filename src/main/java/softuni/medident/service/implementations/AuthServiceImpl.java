package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Patient;
import softuni.medident.data.models.Role;
import softuni.medident.data.repositories.PatientRepository;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.PatientRegisterServiceModel;
import softuni.medident.service.services.AuthService;
import softuni.medident.service.services.HashService;
import softuni.medident.service.services.ValidatorService;

@Service
public class AuthServiceImpl implements AuthService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;
    private final ValidatorService validatorService;

    @Autowired
    public AuthServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, HashService hashService, ValidatorService validatorService) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.hashService = hashService;
        this.validatorService = validatorService;
    }

    @Override
    public void registerPatient(PatientRegisterServiceModel patientModel) throws Exception {

        if (!this.validatorService.isValid(patientModel)){
            throw new Exception("Not a valid user");
        }

        Patient patient = this.modelMapper.map(patientModel, Patient.class);
        patient.setPassword(this.hashService.hash(patient.getPassword()));
        if (patientRepository.count() < 1){
            patient.setRole(Role.ADMIN);
        } else {
            patient.setRole(Role.USER);
        }
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
