package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.medident.constants.RoleConstants;
import softuni.medident.data.models.Address;
import softuni.medident.data.models.Role;
import softuni.medident.data.models.User;
import softuni.medident.data.repositories.AddressRepository;
import softuni.medident.data.repositories.RoleRepository;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.AddressServiceModel;
import softuni.medident.service.models.UserProfileServiceModel;
import softuni.medident.service.models.UserRegisterServiceModel;
import softuni.medident.service.services.AppointmentService;
import softuni.medident.service.services.UserService;
import softuni.medident.service.services.HashService;
import softuni.medident.service.services.AuthValidatorService;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;
    private final AuthValidatorService authValidatorService;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           HashService hashService, AuthValidatorService authValidatorService, RoleRepository roleRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.hashService = hashService;
        this.authValidatorService = authValidatorService;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void register(UserRegisterServiceModel serviceModel) throws UserNotFoundException {
        if (!this.authValidatorService.isValid(serviceModel)){
            throw new UserNotFoundException("Not a valid user");
        }

        this.seedRolesInDb();

        User user = this.modelMapper.map(serviceModel, User.class);
        user.setPassword(this.hashService.hash(user.getPassword()));
        this.setRole(user);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserProfileServiceModel getProfile(String username) {
        User user = this.userRepository.findByUsername(username);
        return this.modelMapper.map(user, UserProfileServiceModel.class);
    }

    @Override
    public void editProfile(UserProfileServiceModel serviceModel) {
        User user = this.userRepository.findByUsername(serviceModel.getUsername());
        user.setAge(serviceModel.getAge());
        user.setPhoneNumber(serviceModel.getPhoneNumber());
        user.setImageUrl(serviceModel.getImageUrl());
        this.userRepository.save(user);
        this.modelMapper.map(user, UserProfileServiceModel.class);
    }

    @Override
    public UserProfileServiceModel getByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return this.modelMapper.map(user, UserProfileServiceModel.class);
    }

    @Override
    public UserProfileServiceModel getById(String id) throws UserNotFoundException {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return this.modelMapper.map(user, UserProfileServiceModel.class);
    }

    @Override
    public void addAddress(UserProfileServiceModel userServiceModel, AddressServiceModel addressServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        Address address = this.modelMapper.map(addressServiceModel, Address.class);
        user.setAddress(address);
        this.addressRepository.saveAndFlush(address);
        this.userRepository.saveAndFlush(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }

    private void seedRolesInDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role(RoleConstants.ROOT_ROLE));
            this.roleRepository.saveAndFlush(new Role(RoleConstants.ADMIN_ROLE));
            this.roleRepository.saveAndFlush(new Role(RoleConstants.USER_ROLE));
        }
    }

    private void setRole(User user) {
        if (userRepository.count() == 0) {
            user.setAuthorities(new HashSet<>(roleRepository.findAll()));
        } else {
            Role role = roleRepository.findByAuthority(RoleConstants.USER_ROLE);
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(role);
        }
    }
}
