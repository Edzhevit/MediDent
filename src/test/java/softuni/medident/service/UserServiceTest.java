package softuni.medident.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.medident.constants.Constants;
import softuni.medident.data.models.Gender;
import softuni.medident.data.models.User;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.base.ServiceTestBase;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.UserService;
import softuni.medident.service.services.AuthValidatorService;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest extends ServiceTestBase {

    @MockBean
    UserRepository userRepository;

    @MockBean
    AuthValidatorService validatorService;

    @Autowired
    UserService userService;

    @Test
    void registerUser_whenIsNotValid_shouldThrow(){
        UserRegisterServiceModel serviceModel = getUserRegisterServiceModel();

        Mockito.when(validatorService.isValid(serviceModel))
                .thenReturn(false);

        assertThrows(
                UserNotFoundException.class,
                () -> userService.register(serviceModel));
    }

    @Test
    void registerUser_whenCreatingFirstUser_shouldBeAdmin() throws Exception, UserNotFoundException {
        UserRegisterServiceModel serviceModel = getUserRegisterServiceModel();

        Mockito.when(validatorService.isValid(serviceModel))
                .thenReturn(true);

        userService.register(serviceModel);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).saveAndFlush(argument.capture());

        User user = argument.getValue();
        assertNotNull(user);
    }

    private UserRegisterServiceModel getUserRegisterServiceModel(){
        return new UserRegisterServiceModel(Constants.DEFAULT_USERNAME,
                Constants.DEFAULT_PASSWORD, Constants.DEFAULT_PASSWORD, Constants.DEFAULT_EMAIL, Constants.DEFAULT_PHONE_NUMBER,
                Constants.DEFAULT_AGE, Gender.MALE, new HashSet<>());
    }
}
