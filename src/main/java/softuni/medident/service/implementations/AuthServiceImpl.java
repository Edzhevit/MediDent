package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.User;
import softuni.medident.data.models.Role;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.AuthService;
import softuni.medident.service.services.HashService;
import softuni.medident.service.services.ValidatorService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;
    private final ValidatorService validatorService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, HashService hashService, ValidatorService validatorService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.hashService = hashService;
        this.validatorService = validatorService;
    }

    @Override
    public void registerUser(UserRegisterServiceModel serviceModel) throws Exception {

        if (!this.validatorService.isValid(serviceModel)){
            throw new Exception("Not a valid user");
        }

        User user = this.modelMapper.map(serviceModel, User.class);
        user.setPassword(this.hashService.hash(user.getPassword()));
        if (userRepository.count() < 1){
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public LoginUserServiceModel login(UserRegisterServiceModel userServiceModel) throws Exception {
        String hashPassword = this.hashService.hash(userServiceModel.getPassword());
        return this.userRepository
                .findByEmailAndPassword(userServiceModel.getEmail(), hashPassword)
                .map(user -> new LoginUserServiceModel(userServiceModel.getEmail()))
                .orElseThrow(() -> new Exception("Invalid User"));

    }
}
