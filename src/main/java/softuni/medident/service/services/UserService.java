package softuni.medident.service.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.AddressServiceModel;
import softuni.medident.service.models.UserProfileServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;

public interface UserService extends UserDetailsService {

    void register(UserRegisterServiceModel user) throws Exception, UserNotFoundException;

    UserProfileServiceModel getProfile(String username);

    void editProfile(UserProfileServiceModel serviceModel);

    UserProfileServiceModel getByUsername(String username);

    UserProfileServiceModel getById(String id) throws UserNotFoundException;

    void addAddress(UserProfileServiceModel serviceModel, AddressServiceModel addressServiceModel);

}
