package softuni.medident.service.services;

import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;

public interface AuthService {

    void registerUser(UserRegisterServiceModel patient) throws Exception;

    LoginUserServiceModel login(UserRegisterServiceModel patient) throws Exception;


}
