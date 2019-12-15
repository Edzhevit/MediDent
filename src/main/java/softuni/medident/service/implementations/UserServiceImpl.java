package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.User;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.RoleService;
import softuni.medident.service.services.UserService;
import softuni.medident.service.services.HashService;
import softuni.medident.service.services.AuthValidatorService;

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
    public User register(UserRegisterServiceModel serviceModel) throws UserNotFoundException {
        if (!this.authValidatorService.isValid(serviceModel)){
            throw new UserNotFoundException("Not a valid user");
        }

        this.roleService.seedRolesInDb();

        //TODO returns different instance of ROLE

        if (userRepository.count() == 0){
            serviceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            serviceModel.setAuthorities(Set.of(this.roleService.findByAuthority("USER")));
        }

        User user = this.modelMapper.map(serviceModel, User.class);
        user.setPassword(this.hashService.hash(user.getPassword()));
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
