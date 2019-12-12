package softuni.medident.service.implementations.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.AuthValidatorService;

import javax.validation.Validator;

@Service
public class AuthValidatorServiceImpl implements AuthValidatorService {

    private final Validator validator;
    private final UserRepository userRepository;

    @Autowired
    public AuthValidatorServiceImpl(UserRepository userRepository, Validator validator){
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean isValid(UserRegisterServiceModel user) {
        return this.arePasswordsValid(user.getPassword(), user.getConfirmPassword()) &&
                this.isEmailFree(user.getEmail()) &&
                isInputFieldsValid(user);
    }

    private boolean isInputFieldsValid(UserRegisterServiceModel user){
        return this.validator.validate(user).size() == 0;
    }

    private boolean isEmailFree(String email) {
        return !this.userRepository.existsByEmail(email);
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }


}
