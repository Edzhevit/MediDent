package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Product;
import softuni.medident.data.models.User;
import softuni.medident.data.repositories.ProductRepository;
import softuni.medident.data.repositories.UserRepository;
import softuni.medident.exception.ProductNotFoundException;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.ProductServiceModel;
import softuni.medident.service.services.ProductService;
import softuni.medident.service.services.ProductValidatorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ProductValidatorService productValidatorService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, ModelMapper modelMapper, ProductValidatorService productValidatorService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.productValidatorService = productValidatorService;
    }

    @Override
    public List<ProductServiceModel> getAllProducts() {
        return this.productRepository.findAll()
                .stream().map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct(ProductServiceModel serviceModel) throws ProductNotFoundException {
        if (!productValidatorService.isValid(serviceModel)){
            throw new ProductNotFoundException("There is no such product");
        }
        Product product = this.modelMapper.map(serviceModel, Product.class);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public ProductServiceModel getById(String id) {
        Product product = this.productRepository.getById(id);

        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public void addToUserById(String id, String username) throws UserNotFoundException, ProductNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null){
            throw new UserNotFoundException("No such user");
        }

        Product product = this.productRepository.getById(id);
        if (product == null){
            throw new ProductNotFoundException("No such product");
        }

        boolean hasProduct = false;

        for (Product userProduct : user.getProducts()) {
            if (userProduct.getModel().equals(product.getModel())){
                hasProduct = true;
                break;
            }
        }

        if (!hasProduct) {
            user.getProducts().add(product);
            userRepository.saveAndFlush(user);
        }
    }

    //TODO

    @Override
    public List<ProductServiceModel> getProductsForUser(String username) {
        return this.productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductServiceModel serviceModel =this.modelMapper.map(product, ProductServiceModel.class);
                    if (product.getUsers() != null){
                        User user = product.getUsers()
                                .stream()
                                .filter(u -> u.getUsername().equals(username))
                                .findAny()
                                .orElse(null);
                        serviceModel.setOwned(user != null);
                    }
                    return serviceModel;
                }).collect(Collectors.toList());
    }
}
