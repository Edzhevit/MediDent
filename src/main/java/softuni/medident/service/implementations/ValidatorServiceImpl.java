package softuni.medident.service.implementations;

import org.springframework.stereotype.Service;
import softuni.medident.data.repositories.PatientRepository;
import softuni.medident.service.models.PatientRegisterServiceModel;
import softuni.medident.service.services.ValidatorService;

import javax.validation.Validation;
import javax.validation.Validator;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    private Validator validator;
    private PatientRepository patientRepository;

    public ValidatorServiceImpl(PatientRepository patientRepository){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.patientRepository = patientRepository;
    }

    @Override
    public Boolean isValid(PatientRegisterServiceModel user) {
        return this.arePasswordsValid(user.getPassword(), user.getConfirmPassword()) &&
                this.isEmailFree(user.getEmail()) &&
                isInputFieldsValid(user);
    }

    private boolean isInputFieldsValid(PatientRegisterServiceModel user){
        return this.validator.validate(user).size() == 0;
    }

    private boolean isEmailFree(String email) {
        return !this.patientRepository.existsByEmail(email);
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }


}
