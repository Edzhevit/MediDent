package softuni.medident.service.validation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.medident.constants.Constants;
import softuni.medident.data.models.Gender;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.service.base.ServiceTestBase;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.AuthValidatorService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthValidatorServiceTest extends ServiceTestBase {

    @MockBean
    UserRepository repository;
    @Autowired
    AuthValidatorService service;


    @Test
    void isValid_whenUsernameIsNull_ShouldReturnFalse(){
        UserRegisterServiceModel user = new UserRegisterServiceModel (
                null, Constants.DEFAULT_PASSWORD, Constants.DEFAULT_PASSWORD,
                Constants.DEFAULT_EMAIL, Gender.MALE,
//                Constants.DEFAULT_IMAGE_URL,
                new HashSet<>());
        boolean isValid = service.isValid(user);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenPasswordDontMatch_ShouldReturnFalse(){
        UserRegisterServiceModel user = new UserRegisterServiceModel (
                Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD, Constants.PASSWORD_NOT_MATCH,
                Constants.DEFAULT_EMAIL, Gender.MALE,
//                Constants.DEFAULT_IMAGE_URL,
                new HashSet<>());
        boolean isValid = service.isValid(user);
        assertFalse(isValid);
    }

    // Not really sure about this test
    @Test
    void isValid_whenMailIsNull_ShouldReturnFalse(){
        String email = Constants.DEFAULT_EMAIL;
        UserRegisterServiceModel user = new UserRegisterServiceModel (
                Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD, Constants.DEFAULT_PASSWORD,
                Constants.DEFAULT_EMAIL, Gender.MALE,
//                Constants.DEFAULT_IMAGE_URL,
                new HashSet<>());

        Mockito.when(repository.existsByEmail(email))
                .thenReturn(true);

        assertFalse(service.isValid(user));
    }

    @Test
    void isValid_whenAllIsValid_ShouldReturnTrue(){
        UserRegisterServiceModel user = new UserRegisterServiceModel (
                Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD, Constants.DEFAULT_PASSWORD,
                Constants.DEFAULT_EMAIL, null,
//                Constants.DEFAULT_IMAGE_URL,
                new HashSet<>());
        boolean isValid = service.isValid(user);
        assertTrue(isValid);
    }
}
