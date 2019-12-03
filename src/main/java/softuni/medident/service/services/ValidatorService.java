package softuni.medident.service.services;

import softuni.medident.service.models.UserRegisterServiceModel;

public interface ValidatorService {

    Boolean isValid(UserRegisterServiceModel object);

}
