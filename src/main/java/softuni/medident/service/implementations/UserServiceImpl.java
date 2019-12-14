package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.User;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.LoginUserServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.RoleService;
import softuni.medident.service.services.UserService;
import softuni.medident.service.services.HashService;
import softuni.medident.service.services.AuthValidatorService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;
    private final AuthValidatorService authValidatorService;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           HashService hashService, AuthValidatorService authValidatorService,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.hashService = hashService;
        this.authValidatorService = authValidatorService;
        this.roleService = roleService;
    }

    @Override
    public User register(UserRegisterServiceModel serviceModel) throws Exception {
        if (!this.authValidatorService.isValid(serviceModel)){
            throw new UserNotFoundException("Not a valid user");
        }

        this.roleService.seedRolesInDb();

        if (userRepository.count() < 1){
            serviceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            serviceModel.setAuthorities(new HashSet<>());
            serviceModel.getAuthorities().add(this.roleService.findByAuthority("USER"));
        }

        User user = this.modelMapper.map(serviceModel, User.class);
        user.setPassword(this.hashService.hash(user.getPassword()));

        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public LoginUserServiceModel login(UserRegisterServiceModel userServiceModel) throws Exception {
        String hashPassword = this.hashService.hash(userServiceModel.getPassword());
        return this.userRepository
                .findByUsernameAndPassword(userServiceModel.getUsername(), hashPassword)
                .map(user -> new LoginUserServiceModel(userServiceModel.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("Invalid User"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
