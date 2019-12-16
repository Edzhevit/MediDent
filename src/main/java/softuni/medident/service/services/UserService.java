package softuni.medident.service.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.medident.data.models.User;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.UserProfileServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;

public interface UserService extends UserDetailsService {

    User register(UserRegisterServiceModel user) throws Exception, UserNotFoundException;

    UserProfileServiceModel getProfile(String username);

//    LoginUserServiceModel login(UserRegisterServiceModel patient) throws Exception;


}
