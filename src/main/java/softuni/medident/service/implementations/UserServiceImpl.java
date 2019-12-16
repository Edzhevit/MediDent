package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.PatientHistory;
import softuni.medident.data.models.Role;
import softuni.medident.data.models.User;
import softuni.medident.data.repositories.RoleRepository;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.UserProfileServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.RoleService;
import softuni.medident.service.services.UserService;
import softuni.medident.service.services.HashService;
import softuni.medident.service.services.AuthValidatorService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;
    private final AuthValidatorService authValidatorService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           HashService hashService, AuthValidatorService authValidatorService,
                           RoleService roleService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.hashService = hashService;
        this.authValidatorService = authValidatorService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @Override
    public User register(UserRegisterServiceModel serviceModel) throws UserNotFoundException {
        if (!this.authValidatorService.isValid(serviceModel)){
            throw new UserNotFoundException("Not a valid user");
        }

        this.seedRolesInDb();

        User user = this.modelMapper.map(serviceModel, User.class);
        user.setPassword(this.hashService.hash(user.getPassword()));
        this.setRole(user);

        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserProfileServiceModel getProfile(String username) {
        User user = this.userRepository.findByUsername(username);
        user.setPatientHistories(List.of(new PatientHistory()));
        return this.modelMapper.map(user, UserProfileServiceModel.class);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }

    private void seedRolesInDb() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ROOT"));
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
        }
    }

    private void setRole(User user) {
        if (userRepository.count() == 0) {
            user.setAuthorities(new HashSet<>(roleRepository.findAll()));
        } else {
            Role role = roleRepository.findByAuthority("USER");
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(role);
        }
    }
}
