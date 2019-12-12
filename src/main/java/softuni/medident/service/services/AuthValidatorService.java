package softuni.medident.service.services;

import softuni.medident.service.models.UserRegisterServiceModel;

public interface AuthValidatorService {

    Boolean isValid(UserRegisterServiceModel object);

}
