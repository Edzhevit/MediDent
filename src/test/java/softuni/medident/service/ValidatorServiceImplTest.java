package softuni.medident.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import softuni.medident.data.models.Gender;
import softuni.medident.data.models.Role;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.service.implementations.ValidatorServiceImpl;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.ValidatorService;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorServiceImplTest {

    Validator validator;
    UserRepository userRepository;
    ValidatorService service;

    @BeforeEach
    void setupTest(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        userRepository = Mockito.mock(UserRepository.class);
        service = new ValidatorServiceImpl(userRepository, validator);
    }

    @Test
    void isValid_whenNameIsNull_ShouldReturnFalse(){
        UserRegisterServiceModel user = new UserRegisterServiceModel (
                null, "Valid Last Name", "123QWEqwe_~#", "123QWEqwe_~#",
                "valid@email.bg", "0888112233", 25, Gender.MALE, Role.USER);
        boolean isValid = service.isValid(user);
        assertFalse(isValid);
    }
}
